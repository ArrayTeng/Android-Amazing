package com.tengfei.easybinder;

public class EasyBinder {

    static {
        System.loadLibrary("native-lib");
    }

    public native void write();

    public native String read();
}
