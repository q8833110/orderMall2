package com.order.mall.data.base;

import com.order.mall.data.SharedPreferencesHelp;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class LoginInterceptor implements Interceptor {

    private SharedPreferencesHelp sharedPreferencesHelp ;

    @Inject
    public LoginInterceptor(SharedPreferencesHelp sharedPreferencesHelp){
        this.sharedPreferencesHelp = sharedPreferencesHelp ;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
