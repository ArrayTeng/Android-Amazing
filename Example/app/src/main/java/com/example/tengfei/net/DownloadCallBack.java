package com.example.tengfei.net;

import java.io.File;
import java.io.IOException;

/**
 * @author tengfei
 * date 2019/3/2 12:54 PM
 * email tengfeigo@outlook.com
 * description
 */
public interface DownloadCallBack {

    void downloadSuccess(File file);

    void downloadFail(IOException e);
}
