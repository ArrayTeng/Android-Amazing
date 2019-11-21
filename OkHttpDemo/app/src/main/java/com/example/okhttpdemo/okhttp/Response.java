package com.example.okhttpdemo.okhttp;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author tengfei
 * date 2019-11-20 10:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Response {

    private InputStream inputStream;

    public Response(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    @Override
    public String toString() {
        String str = "";
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            str = result.toString(StandardCharsets.UTF_8.name());
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
