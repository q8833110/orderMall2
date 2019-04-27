package com.order.mall;

import android.app.Application;
import android.content.Context;

import com.order.mall.di.Module.ApplicationModule;
import com.order.mall.di.compent.ApplicationComponent;
import com.order.mall.di.compent.DaggerApplicationComponent;

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
