/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package okhttp3.internal.connection

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.RealInterceptorChain

/**
 * Opens a connection to the target server and proceeds to the next interceptor. The network might
 * be used for the returned response, or to validate a cached response with a conditional GET.
 */
//建立连接拦截器 打开与目标服务器的链接，个人认为OkHttp的核心在于 dispatch 任务分发以及如何建立一个连接，包括连接复用,代码量很少，但其实
//都是 OkHttp 帮我们封装起来了
object ConnectInterceptor : Interceptor {
  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    /**
     * 前置工作 start
     */
    val realChain = chain as RealInterceptorChain
    //创建一个 Exchange 对象，Exchange 从字面意思上理解是交换的意思，用在这里其实也很贴切，客户端与服务端之间的数据交换
    val exchange = realChain.call.initExchange(chain)
    //copy出一个新的 Chain 给 exchange 赋值进行后置操作将 request 发送给下一个拦截器 CallServerInterceptor
    val connectedChain = realChain.copy(exchange = exchange)
    /**
     * 中置工作，把任务往后推
     */
    return connectedChain.proceed(realChain.request)
  }
}
