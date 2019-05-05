package com.order.mall.ui.activity;

import android.content.Intent;
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
import butterknife.Unbinder;

/**
 * 订单提交
 */
public class ReportOrderSubActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.pay_style)
    TextView payStyle;
    @BindView(R.id.iv_pay_style)
    ImageView ivPayStyle;
    @BindView(R.id.payment_type)
    TextView paymentType;
    @BindView(R.id.iv_amount)
    ImageView ivAmount;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.payment_qrcode)
    TextView paymentQrcode;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.pay1)
    ImageView pay1;
    @BindView(R.id.pay2)
    ImageView pay2;

    Unbinder unbinder ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_order_submit);
        unbinder = ButterKnife.bind(this);
        init();
    }


    @OnClick(R.id.tv_already_pay)
    public void pay(){
        Intent intent = new Intent(this ,RechargeActivity.class);
        startActivity(intent);
    }
    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    private void init() {
        tvTitle.setText("详细详情");
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
