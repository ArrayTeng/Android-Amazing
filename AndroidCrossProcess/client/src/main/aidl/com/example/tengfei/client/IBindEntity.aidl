// IBindEntity.aidl
package com.example.tengfei.client;
import com.example.tengfei.client.NumberEntity;
import com.example.tengfei.client.IListener;

interface IBindEntity {

    void registerListener(IListener listener);

    void unRegisterListener(IListener listener);
}
