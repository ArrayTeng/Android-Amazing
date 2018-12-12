package com.example.tengfei.androidcrossprocess.blog.binderpool;

import android.os.RemoteException;

import com.example.tengfei.androidcrossprocess.ICompute;

public class ComputeImpl extends ICompute.Stub {

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
