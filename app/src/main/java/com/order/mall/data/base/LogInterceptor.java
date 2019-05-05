package com.order.mall.data.base;

import android.support.annotation.NonNull;
import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Log拦截器
 *
 * @author zcd
 * @date 17/9/14
 */

public class LogInterceptor implements Interceptor {
    private final static String TAG = LogInterceptor.class.getSimpleName();
    private final static boolean DEBUG = true;

    private final static String GET = "GET";
    private final static String POST = "POST";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request newRequest = chain.request();
        String url = newRequest.url().toString();
        String header = newRequest.headers().toString();
        Response response = chain.proceed(newRequest);
        String content;
        MediaType mediaType;
        if (response.body() != null) {
            content = response.body().string();
            mediaType = response.body().contentType();
        } else {
            content = "";
            mediaType = MediaType.parse("application/json;charset=UTF-8");
        }
        int code = response.code();
        if (DEBUG) {
            if (!newRequest.method().equals(GET)) {
                String requestContent = bodyToString(newRequest.body());
                Log.d(TAG, "url: " + url + "\n" + "code: " + code + "\n" + "request header: " + header + "\n" + "request body: " + requestContent + "\n" + "response header: "
                        + response.headers().toString() + "\n" + "response body: " + content);
            } else {
                Log.d(TAG, "url: " + url + "\n" + "code: " + code + "\n" + "request header: " + header + "\n" + "response body: " + content);
            }
        }

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }

    private String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            request.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
