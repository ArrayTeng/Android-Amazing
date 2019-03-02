package com.example.tengfei.net;

import android.content.Context;

import com.example.common.okhttp.download.Utils;

import java.io.File;

/**
 * @author tengfei
 * date 2019/3/2 1:56 PM
 * email tengfeigo@outlook.com
 * description
 */
public class FileManager {

    private Context context;
    private File rootFile;

    private FileManager() {
    }

    public static FileManager instance() {
        return Holder.FILE_MANAGER;
    }

    private static final class Holder {
        private static final FileManager FILE_MANAGER = new FileManager();
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public File getFile(String url) {
        String fileName = Utils.md5Uri(url);
        if (rootFile == null) {
            rootFile = context.getCacheDir();
        }
        return new File(rootFile, fileName);
    }

}
