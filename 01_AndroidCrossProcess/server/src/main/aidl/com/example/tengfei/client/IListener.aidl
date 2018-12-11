// IListener.aidl
package com.example.tengfei.client;
import com.example.tengfei.client.NumberEntity;


interface IListener {

    void onNewInFoArrived(in NumberEntity numberEntity);
}
