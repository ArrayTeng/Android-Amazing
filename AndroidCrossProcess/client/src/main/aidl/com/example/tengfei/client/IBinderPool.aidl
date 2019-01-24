// IBinderPool.aidl 定义 aidl 文件，用于在客户端调用 queryBinder 方法时通过传入的 code 来返回客户端所需要的 Binder 对象
package com.example.tengfei.client;


interface IBinderPool {

   IBinder queryBinder(int code);
}
