package com.order.mall;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.order.mall.di.Module.ApplicationModule;
import com.order.mall.di.compent.ApplicationComponent;
import com.order.mall.di.compent.DaggerApplicationComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

public class MyApp extends Application {
    public static Context application;

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this.getApplicationContext();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
}
