/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.order.mall.di.Module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.base.LoginInterceptor;
import com.order.mall.di.ApplicationContext;
import com.order.mall.util.Constant;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    LoginInterceptor providesLoginInterceptor(SharedPreferencesHelp sharedPreferencesHelp) {
        return new LoginInterceptor(sharedPreferencesHelp);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(LoginInterceptor logInterceptor) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(logInterceptor)
                .connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(Constant.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    Gson providerGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    SharedPreferencesHelp provideSharedPreferenceHelp(Application context, Gson gson) {
        return new SharedPreferencesHelp(context, gson);
    }
}
