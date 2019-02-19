package com.example.common.okhttp.download;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.File;

/**
 * @author tengfei
 * date 2019/2/16 9:31 PM
 * email tengfeigo@outlook.com
 * description
 */
public class FileManager {

    private File mRootFile;
    private Context mContext;

    private FileManager(){}

    public static FileManager getInstance(){
        return Holder.FILE_MANAGER;
    }

    private static class Holder{
        @SuppressLint("StaticFieldLeak")
        private static final FileManager FILE_MANAGER = new FileManager();
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void rootDir(File file) {
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
        if (file.exists() && file.isDirectory()) {
            mRootFile = file;
        }
    }

    public File getFile(String url) {
        String fileName = Utils.md5Uri(url);
        if (mRootFile == null) {
            mRootFile = mContext.getCacheDir();
        }
        return new File(mRootFile, fileName);
    }
}
