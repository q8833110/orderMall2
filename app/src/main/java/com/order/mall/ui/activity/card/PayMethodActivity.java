package com.order.mall.ui.activity.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.user.ChangePasswordActivity;
import com.order.mall.ui.adapter.PayMethodAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收款方式
 */
public class PayMethodActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    Unbinder unbinder;
    PayMethodAdapter adapter;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.add_bank)
    ImageView addBank;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.rv_bank)
    RecyclerView rvBank;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.tv_zhifubao)
    TextView tvZhifubao;
    @BindView(R.id.add_zhifubao)
    ImageView addZhifubao;
    @BindView(R.id.rl_zhifubao)
    RelativeLayout rlZhifubao;
    @BindView(R.id.rv_zhifubao)
    RecyclerView rvZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.add_weixin)
    ImageView addWeixin;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWx;
    @BindView(R.id.rv_weixin)
    RecyclerView rvWeixin;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_method);
        unbinder = ButterKnife.bind(this);
        initRecy();
    }

    private void initRecy() {
        List<PayMethodAdapter.Data> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add(new PayMethodAdapter.Data());
        }
        adapter = new PayMethodAdapter(this, R.layout.item_paymentod, data);
        rvBank.setLayoutManager(new LinearLayoutManager(this));
        rvBank.setAdapter(adapter);
    }

    @OnClick(R.id.rl_password)
    public void toPassword() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
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
