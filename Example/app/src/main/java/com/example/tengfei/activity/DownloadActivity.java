package com.example.tengfei.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;

import com.example.common.okhttp.download.DownloadCallBack;
import com.example.common.okhttp.download.DownloadDispatcher;
import com.example.common.okhttp.download.FileManager;
import com.example.tengfei.BaseActivity;
import com.example.tengfei.BuildConfig;
import com.example.tengfei.R;

import java.io.File;
import java.io.IOException;

/**
 * @author tengfei
 * date 2019/2/19 9:32 PM
 * email tengfeigo@outlook.com
 * description
 */
public class DownloadActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public int setContentLayoutView() {
        return R.layout.activity_download;
    }

    @Override
    public void initOperation(@Nullable Bundle savedInstanceState) {
        findViewById(R.id.bt_download).setOnClickListener(this);
    }


    private void installFile(File  file) {
        // 核心是下面几句代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file );
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_download:
                FileManager.getInstance().init(this);
                DownloadDispatcher.getInstance().startDownload("http://dapp3.baidu.com/data/wisegame/60c7bb36de3d14e4/jinritoutiao_713.apk", new DownloadCallBack() {
                    @Override
                    public void onFailure(IOException e) {
                        Log.i("tmd",e.getMessage());
                    }

                    @Override
                    public void onSuccess(File file) {
                        installFile(file);
                        Log.i("tmd","onSuccess"+file.toString());
                    }
                });
                break;
            default:
                break;
        }
    }
}
