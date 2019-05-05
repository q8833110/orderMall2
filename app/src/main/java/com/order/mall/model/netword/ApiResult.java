package com.order.mall.model.netword;

import android.content.Intent;

import java.lang.Error;

/**
 * ApiResult
 *
 * @author zcd
 * @date 17/9/14
 */

public class ApiResult<T> {
    T data;
    int code;
    String  message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
