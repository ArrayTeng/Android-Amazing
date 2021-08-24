/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okhttp3.internal.connection

import java.io.IOException
import java.net.Socket
import okhttp3.Address
import okhttp3.EventListener
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Route
import okhttp3.internal.canReuseConnectionFor
import okhttp3.internal.closeQuietly
import okhttp3.internal.http.ExchangeCodec
import okhttp3.internal.http.RealInterceptorChain
import okhttp3.internal.http2.ConnectionShutdownException
import okhttp3.internal.http2.ErrorCode
import okhttp3.internal.http2.StreamResetException

/**
 * Attempts to find the connections for an exchange and any retries that follow. This uses the
 * following strategies:
 *
 *  1. If the current call already has a connection that can satisfy the request it is used. Using
 *     the same connection for an initial exchange and its follow-ups may improve locality.
 *
 *  2. If there is a connection in the pool that can satisfy the request it is used. Note that it is
 *     possible for shared exchanges to make requests to different host names! See
 *     [RealConnection.isEligible] for details.
 *
 *  3. If there's no existing connection, make a list of routes (which may require blocking DNS
 *     lookups) and attempt a new connection them. When failures occur, retries iterate the list of
 *     available routes.
 *
 * If the pool gains an eligible connection while DNS, TCP, or TLS work is in flight, this finder
 * will prefer pooled connections. Only pooled HTTP/2 connections are used for such de-duplication.
 *
 * It is possible to cancel the finding process.
 *
 * Instances of this class are not thread-safe. Each instance is thread-confined to the thread
 * executing [call].
 */
class ExchangeFinder(
  private val connectionPool: RealConnectionPool,
  internal val address: Address,
  private val call: RealCall,
  private val eventListener: EventListener
) {
  private var routeSelection: RouteSelector.Selection? = null
  private var routeSelector: RouteSelector? = null
  private var refusedStreamCount = 0
  private var connectionShutdownCount = 0
  private var otherFailureCount = 0
  private var nextRouteToTry: Route? = null

  fun find(
    client: OkHttpClient,
    chain: RealInterceptorChain
  ): ExchangeCodec {
    try {
      /**
       * 1、找到一个健康的连接，这个健康的连接的意思你可以理解为是一个当前可用的 RealConnection 对象
       */
      val resultConnection = findHealthyConnection(
          connectTimeout = chain.connectTimeoutMillis,
          readTimeout = chain.readTimeoutMillis,
          writeTimeout = chain.writeTimeoutMillis,
          pingIntervalMillis = client.pingIntervalMillis,
          connectionRetryEnabled = client.retryOnConnectionFailure,
          doExtensiveHealthChecks = chain.request.method != "GET"
      )
      /**
       * 2、基于这个连接去创建一个编码解码器,确定编码规则
       * 是按照Http1的方式去写请求和读响应还是按照Http2的方式去写请求和读响应
       */
      return resultConnection.newCodec(client, chain)
    } catch (e: RouteException) {
      trackFailure(e.lastConnectException)
      throw e
    } catch (e: IOException) {
      trackFailure(e)
      throw RouteException(e)
    }
  }

  /**
   * Finds a connection and returns it if it is healthy. If it is unhealthy the process is repeated
   * until a healthy connection is found.
   */
  @Throws(IOException::class)
  private fun findHealthyConnection(
    connectTimeout: Int,
    readTimeout: Int,
    writeTimeout: Int,
    pingIntervalMillis: Int,
    connectionRetryEnabled: Boolean,
    doExtensiveHealthChecks: Boolean
  ): RealConnection {
    /**
     * 反复的尝试去拿到一个健康的连接，先去拿到一个连接然后去验证是否“健康”，如果不健康就去 container 一下重新来一次循环，
     * 不要被 while(true) 欺骗，这里撑死了极端情况下只会执行 5 次，因为 findConnection 里一共只有 5 种情况去获取一个可用连接，RealCall中获取，连接池中获取，直接自己新建
     */
    while (true) {
      /**
       * 1、拿到一个可用连接
       * question：什么是可用连接？
       */
      val candidate = findConnection(
          connectTimeout = connectTimeout,
          readTimeout = readTimeout,
          writeTimeout = writeTimeout,
          pingIntervalMillis = pingIntervalMillis,
          connectionRetryEnabled = connectionRetryEnabled
      )

      /**
       * 2、验证这个连接是够"健康"
       * question：怎么判断这个连接是健康的？
       */
      if (candidate.isHealthy(doExtensiveHealthChecks)) {
        return candidate
      }

      /**
       * 3、如果不健康执行下面的逻辑 continue 继续一次循环，以下的代码忽略不计，我看了下每个版本的有点区别，但不是我们看的重点
       */

      // If it isn't, take it out of the pool.
      candidate.noNewExchanges()

      // Make sure we have some routes left to try. One example where we may exhaust all the routes
      // would happen if we made a new connection and it immediately is detected as unhealthy.
      if (nextRouteToTry != null) continue

      val routesLeft = routeSelection?.hasNext() ?: true
      if (routesLeft) continue

      val routesSelectionLeft = routeSelector?.hasNext() ?: true
      if (routesSelectionLeft) continue

      throw IOException("exhausted all routes")
    }
  }

  /**
   *
   * 在 findConnection 中okhttp会多次去尝试获取可用连接，最多5次，通过5种不同方式去获取可用连接
   */
  @Throws(IOException::class)
  private fun findConnection(
    connectTimeout: Int,
    readTimeout: Int,
    writeTimeout: Int,
    pingIntervalMillis: Int,
    connectionRetryEnabled: Boolean
  ): RealConnection {
    /**
     * 如果请求已经被取消了直接抛出异常
     */
    if (call.isCanceled()) throw IOException("Canceled")

    /**
     *1、首先去看下 RealCall 里有没有一个可用连接，不过在第一时间里是没有的 ， 在你第一次利用 OkHttp 发起
     * 网络请求的时候 call.connection 的值为 null
     */
    val callConnection = call.connection
    if (callConnection != null) {
      var toClose: Socket? = null
      synchronized(callConnection) {
        if (callConnection.noNewExchanges || !sameHostAndPort(callConnection.route().address.url)) {
          /**
           * callConnection 不满足条件直接抛弃
           * 1、noNewExchanges 不接受新的请求
           * 2、域名和端口号不相同
           */
          toClose = call.releaseConnectionNoEvents()
        }
      }
      
      //直接重用
      if (call.connection != null) {
        check(toClose == null)
        return callConnection
      }

      // The call's connection was released.
      toClose?.closeQuietly()
      eventListener.connectionReleased(call, callConnection)
    }

    // We need a new connection. Give it fresh stats.
    refusedStreamCount = 0
    connectionShutdownCount = 0
    otherFailureCount = 0

    /**
     * 2、第一次尝试去连接：帮你的call去尝试拿一个放在池里的连接,
     * 这里的 callAcquirePooledConnection 的 routes 入参和 requireMultiplexed 的值一个为null 一个为 false
     * 这里注意下，在下面的操作中要用，routes 是一个 Route 集合，在 Route 里封装了ip地址，代理模式等，
     * requireMultiplexed 表示是否只拿多路复用的连接
     */
    if (connectionPool.callAcquirePooledConnection(address, call, null, false)) {
      val result = call.connection!!  //拿到里一个可用的连接
      eventListener.connectionAcquired(call, result)
      return result
    }

    // Nothing in the pool. Figure out what route we'll try next.
    val routes: List<Route>?
    val route: Route
    if (nextRouteToTry != null) {
      // Use a route from a preceding coalesced connection.
      routes = null
      route = nextRouteToTry!!
      nextRouteToTry = null
    } else if (routeSelection != null && routeSelection!!.hasNext()) {
      // Use a route from an existing route selection.
      routes = null
      route = routeSelection!!.next()
    } else {
      // Compute a new route selection. This is a blocking operation!
      var localRouteSelector = routeSelector
      if (localRouteSelector == null) {
        localRouteSelector = RouteSelector(address, call.client.routeDatabase, call, eventListener)
        this.routeSelector = localRouteSelector
      }
      val localRouteSelection = localRouteSelector.next()
      routeSelection = localRouteSelection
      routes = localRouteSelection.routes

      if (call.isCanceled()) throw IOException("Canceled")

      /**
       * 3、如果拿不到就重新再去拿一次，在重试重定向拦截器中会创建 address address 就是包装你访问的主机和端口  routes 就是指你的ip地址 tcp端口 代理等
       */
      if (connectionPool.callAcquirePooledConnection(address, call, routes, false)) {
        val result = call.connection!!
        eventListener.connectionAcquired(call, result)
        return result
      }

      route = localRouteSelection.next()
    }

    /**
     * 4、自己新建一个 RealConnection 然后去连接
     */
    val newConnection = RealConnection(connectionPool, route)
    call.connectionToCancel = newConnection
    try {
      newConnection.connect(
          connectTimeout,
          readTimeout,
          writeTimeout,
          pingIntervalMillis,
          connectionRetryEnabled,
          call,
          eventListener
      )
    } finally {
      call.connectionToCancel = null
    }
    call.client.routeDatabase.connected(newConnection.route())

    /**
     * 5、在调用一次 callAcquirePooledConnection requireMultiplexed == true 表示只拿Http2的连接
     */
    if (connectionPool.callAcquirePooledConnection(address, call, routes, true)) {
      val result = call.connection!!
      nextRouteToTry = route
      newConnection.socket().closeQuietly()
      eventListener.connectionAcquired(call, result)
      return result
    }

    synchronized(newConnection) {
      connectionPool.put(newConnection)
      call.acquireConnectionNoEvents(newConnection)
    }

    eventListener.connectionAcquired(call, newConnection)
    return newConnection
  }

  fun trackFailure(e: IOException) {
    nextRouteToTry = null
    if (e is StreamResetException && e.errorCode == ErrorCode.REFUSED_STREAM) {
      refusedStreamCount++
    } else if (e is ConnectionShutdownException) {
      connectionShutdownCount++
    } else {
      otherFailureCount++
    }
  }

  /**
   * Returns true if the current route has a failure that retrying could fix, and that there's
   * a route to retry on.
   */
  fun retryAfterFailure(): Boolean {
    if (refusedStreamCount == 0 && connectionShutdownCount == 0 && otherFailureCount == 0) {
      return false // Nothing to recover from.
    }

    if (nextRouteToTry != null) {
      return true
    }

    val retryRoute = retryRoute()
    if (retryRoute != null) {
      // Lock in the route because retryRoute() is racy and we don't want to call it twice.
      nextRouteToTry = retryRoute
      return true
    }

    // If we have a routes left, use 'em.
    if (routeSelection?.hasNext() == true) return true

    // If we haven't initialized the route selector yet, assume it'll have at least one route.
    val localRouteSelector = routeSelector ?: return true

    // If we do have a route selector, use its routes.
    return localRouteSelector.hasNext()
  }

  /**
   * Return the route from the current connection if it should be retried, even if the connection
   * itself is unhealthy. The biggest gotcha here is that we shouldn't reuse routes from coalesced
   * connections.
   */
  private fun retryRoute(): Route? {
    if (refusedStreamCount > 1 || connectionShutdownCount > 1 || otherFailureCount > 0) {
      return null // This route has too many problems to retry.
    }

    val connection = call.connection ?: return null

    synchronized(connection) {
      if (connection.routeFailureCount != 0) return null
      if (!connection.route().address.url.canReuseConnectionFor(address.url)) return null
      return connection.route()
    }
  }

  /**
   * Returns true if the host and port are unchanged from when this was created. This is used to
   * detect if followups need to do a full connection-finding process including DNS resolution, and
   * certificate pin checks.
   */
  fun sameHostAndPort(url: HttpUrl): Boolean {
    val routeUrl = address.url
    return url.port == routeUrl.port && url.host == routeUrl.host
  }
}
