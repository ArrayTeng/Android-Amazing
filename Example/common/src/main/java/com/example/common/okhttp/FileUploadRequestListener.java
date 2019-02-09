package com.example.common.okhttp;

/**
 * @author tengfei
 * date 2019/2/9 11:54 AM
 * email tengfeigo@outlook.com
 * description
 */
public interface FileUploadRequestListener {

    void uploadProgress(long mCurrentProgress,long totalProgress);
}
