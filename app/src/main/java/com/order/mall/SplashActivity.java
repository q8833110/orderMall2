package com.order.mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivSp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivSp = findViewById(R.id.iv_sp);
        Glide.with(this).load(R.mipmap.splash);
    }
}
