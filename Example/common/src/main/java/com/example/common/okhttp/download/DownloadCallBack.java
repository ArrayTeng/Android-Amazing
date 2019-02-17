package com.example.common.okhttp.download;

import java.io.IOException;

import okhttp3.Call;

/**
 * @author tengfei
 * date 2019/2/16 9:43 PM
 * email tengfeigo@outlook.com
 * description
 */
interface DownloadCallBack {
    void onFailure(Call call, IOException e);
}
