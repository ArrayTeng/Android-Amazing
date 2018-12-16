package com.example.tengfei.client.binderpool;

import android.os.RemoteException;
import android.util.Log;


import com.example.tengfei.client.IInFoManager;
import com.example.tengfei.client.Info;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tengfei
 */
public class InfoManagerImpl extends IInFoManager.Stub {

    private CopyOnWriteArrayList copyOnWriteArrayList;

    public InfoManagerImpl(CopyOnWriteArrayList copyOnWriteArrayList) {
        this.copyOnWriteArrayList = copyOnWriteArrayList;
    }

    @Override
    public void addInformation(Info info) throws RemoteException {
        copyOnWriteArrayList.add(info);
    }

    @Override
    public List<Info> getInformationList() throws RemoteException {
        return copyOnWriteArrayList;
    }
}
