package com.example.common.okhttp;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * @author tengfei
 * date 2019/2/9 11:46 AM
 * email tengfeigo@outlook.com
 * description 自定义 RequestBody 实现 okHttp 上传文件监听
 */
public class FileUploadRequestBody extends RequestBody {

    private RequestBody requestBody;

    private long mCurrentProgress;

    private FileUploadRequestListener fileUploadRequestListener;

    public void setFileUploadRequestListener(FileUploadRequestListener fileUploadRequestListener) {
        this.fileUploadRequestListener = fileUploadRequestListener;
    }

    public FileUploadRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public FileUploadRequestBody(RequestBody requestBody, FileUploadRequestListener fileUploadRequestListener) {
        this.requestBody = requestBody;
        this.fileUploadRequestListener = fileUploadRequestListener;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        ForwardingSink mForwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(@NonNull Buffer source, long byteCount) throws IOException {
                if (fileUploadRequestListener != null) {
                    mCurrentProgress += byteCount;
                    fileUploadRequestListener.uploadProgress(mCurrentProgress, contentLength());
                }
                super.write(source, byteCount);
            }
        };
        BufferedSink bufferedSink = Okio.buffer(mForwardingSink);
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }
}
