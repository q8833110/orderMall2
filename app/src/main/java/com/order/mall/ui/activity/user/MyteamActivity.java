package com.order.mall.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.TeamAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyteamActivity extends BaseActivity {
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
    @BindView(R.id.tv_yeji)
    TextView tvYeji;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.rv)
    RecyclerView rv;
    private TeamAdapter adapter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        unbinder = ButterKnife.bind(this);
        init();
        initView();
    }

    private void initView() {
        tvTitle.setText("我的团队");
    }

    private void init() {
        List<TeamAdapter.Data> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new TeamAdapter.Data());
        }
        adapter = new TeamAdapter(this, R.layout.item_team, list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

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
