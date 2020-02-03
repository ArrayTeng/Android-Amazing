package com.example.libnetwork;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author tengfei
 * date 2020-02-03 19:16
 * email arrayadapter.cn@gmail.com
 * description
 */
public class UrlCreator {

    public static String createUrlFromParams(String url, Map<String, Object> params) {

        StringBuilder builder = new StringBuilder();
        builder.append(url);
        if (url.indexOf("?") > 0 || url.indexOf("&") > 0) {
            builder.append("&");
        } else {
            builder.append("?");
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                String value = URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
                builder.append(entry.getKey()).append("=").append(value).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
