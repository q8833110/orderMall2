package com.order.mall.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 充值
 */
public class RechargeActivity extends BaseActivity {

    Unbinder unbinder;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_equal_grade)
    TextView tvEqualGrade;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.line_zhifubao)
    View lineZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.line_weixin)
    View lineWeixin;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.radio_zhifubao)
    RadioButton radioZhifubao;
    @BindView(R.id.radio_weixin)
    RadioButton radioWeixin;
    @BindView(R.id.radio_bank)
    RadioButton radioBank;
    @BindView(R.id.pay)
    TextView pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        unbinder = ButterKnife.bind(this);
        init();
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
