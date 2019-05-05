package com.order.mall.ui.activity.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiangdanPayActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_baodan)
    TextView tvBaodan;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_baodna1)
    TextView tvBaodna1;
    @BindView(R.id.tv_jifen1)
    TextView tvJifen1;
    @BindView(R.id.tv_pay)
    TextView tvPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiangdan);
        ButterKnife.bind(this);
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
