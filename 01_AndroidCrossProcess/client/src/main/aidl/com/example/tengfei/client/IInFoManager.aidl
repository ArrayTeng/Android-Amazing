// IInFoManager.aidl
package com.example.tengfei.client;
import com.example.tengfei.client.Info;


interface IInFoManager {

    void addInformation(in Info info);

    List<Info> getInformationList();
}
