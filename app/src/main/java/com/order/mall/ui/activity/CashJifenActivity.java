package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.cash.CashJifenDetailsActivity;
import com.order.mall.ui.activity.cash.WithdrawalCenterActivity;
import com.order.mall.ui.adapter.PayMentDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CashJifenActivity extends BaseActivity {
    Unbinder unbinder;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.ll_withdrawal)
    LinearLayout llWithdrawal;
    @BindView(R.id.ll_transfer)
    LinearLayout llTransfer;
    @BindView(R.id.ll_l1)
    LinearLayout llL1;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.iv_start1)
    ImageView ivStart1;
    @BindView(R.id.rl_shouzhi)
    RelativeLayout rlShouzhi;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.rv)
    RecyclerView rv;

    private PayMentDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashjifen);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        List<PayMentDetailAdapter.Payments> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PayMentDetailAdapter.Payments());
        }
        adapter = new PayMentDetailAdapter(this, R.layout.item_payment, list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @OnClick(R.id.tv_jifen)
    public void toCashJifenDetail(){
        Intent intent = new Intent(this ,CashJifenDetailsActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.back)
    public void back(){
        this.finish();
    }

    @OnClick(R.id.ll_withdrawal)
    public void toJifen() {
        Intent intent = new Intent(this , WithdrawalCenterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_transfer)
    public void toRecharge() {
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
