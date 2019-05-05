package com.order.mall.ui.activity.cash;

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
 * 积分转账
 */
public class TransferJifenActivity extends BaseActivity {

    Unbinder unbinder;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_cash)
    TextView tvCash;
    @BindView(R.id.tv_baodan)
    TextView tvBaodan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_transfer);
        unbinder = ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    private void initBonus() {
        tvCash.setBackgroundResource(R.drawable.cash_transfer);
        tvBaodan.setBackgroundResource(R.drawable.bonus_transfer);
        tvCash.setText("奖金积分");
        tvBaodan.setText("现金积分");
    }

    private void init() {
        tvTitle.setText("详细信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }
}
