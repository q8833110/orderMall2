package com.order.mall.util;

import com.order.mall.data.base.LogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils singleton;

    private  Retrofit retrofit ;
    private RetrofitUtils() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new LogInterceptor())
                .connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitUtils getInstance() {
        if (singleton == null) {
            singleton = new RetrofitUtils();
        }
        return singleton;
    }


}
