package com.example.okhttpdemo.okhttp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author tengfei
 * date 2019-11-22 10:23
 * email arrayadapter.cn@gmail.com
 * description
 */
public class RequestBody {
    
    public static final String FROM ="multipart/form-data" ;

    private String boundary = createBoundary();
    private String startBoundary = "--"+boundary;


    private String endBoundary = startBoundary+"--";

    private String createBoundary() {
        return "OkHttp"+ UUID.randomUUID().toString();
    }

    String type;


    Map<String,Object> params;

    public RequestBody() {
        this(new Builder());
    }

    public RequestBody(Builder builder) {
        this.type = builder.type;
        params = builder.params;
    }

    public String getType() {
        return type+": boundary = "+boundary;
    }

    public long getLength() {
        long length = 0;
        for(Map.Entry<String,Object> params:params.entrySet()){
            String key = params.getKey();
            Object value = params.getValue();
            if (value instanceof String){
                String postTestStr = getText(key,(String) value);
                length+=postTestStr.getBytes().length;
            }
        }
        if (params.size()!=0){
            length+=endBoundary.getBytes().length;
        }
        return length;
    }

    private String getText(String key, String value) {
        return startBoundary+"\r\n"+
                "Content-Disposition: form-data; name = \""+key+"\"\r\n"+
                "Context-Type: text/plain\r\n"+
                "\r\n"+
                value+
                "\r\n";

    }

    public void onWriteStream(OutputStream outputStream) throws IOException {
        for(Map.Entry<String,Object> entry:params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof String){
                String postTextStr = getText(key,(String)value);
                outputStream.write(postTextStr.getBytes());
            }
        }

        if(params.size()!=0){
            outputStream.write(endBoundary.getBytes());
        }
    }


    public static class Builder {

        String type;

        Map<String,Object> params;

        public Builder() {
            params = new HashMap<>();
        }

        public RequestBody build() {
            return new RequestBody(this);
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }


        public Builder addParam(String key, String value) {
            params.put(key,value);
            return this;
        }
    }
}
