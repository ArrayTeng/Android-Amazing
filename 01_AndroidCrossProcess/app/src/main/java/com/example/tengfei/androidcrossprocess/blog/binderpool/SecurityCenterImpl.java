package com.example.tengfei.androidcrossprocess.blog.binderpool;

import android.os.RemoteException;

import com.example.tengfei.androidcrossprocess.ISecurityCenter;

/**
 * @author tengfei
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub {

    private static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
