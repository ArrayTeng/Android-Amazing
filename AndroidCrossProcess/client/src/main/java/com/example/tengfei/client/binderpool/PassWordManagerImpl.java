package com.example.tengfei.client.binderpool;

import android.os.RemoteException;

import com.example.tengfei.client.IPassWordManager;

/**
 * @author tengfei 简化处理，加解密的操作可以自己实现
 */
public class PassWordManagerImpl extends IPassWordManager.Stub {

    @Override
    public String encryption(String password) throws RemoteException {

        return password;
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return password+"#####";
    }


}
