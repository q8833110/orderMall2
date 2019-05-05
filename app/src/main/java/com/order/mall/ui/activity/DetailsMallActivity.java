package com.order.mall.ui.activity;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsMallActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

    }


    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }
}
