package com.order.mall;

import android.content.Intent;
import android.os.Bundle;

import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.MainActivity;
import com.order.mall.util.RxHelper;

import rx.functions.Action1;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RxHelper.delay(2, new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        });
    }
}
