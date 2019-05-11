package com.order.mall.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.UserTeam;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.TeamAdapter;
import com.order.mall.util.RetrofitUtils;

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
    @BindView(R.id.tv_num)
    TextView mNumTv;

    private TeamAdapter adapter;
    private Unbinder unbinder;
    private IUserApi iUserApi;
    private int userId = 500000;
    private List<UserTeam.UserVoBean> userVoBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        init();
        initView();
        getInfo();
    }

    private void getInfo() {
        addObserver(iUserApi.userTeamInfo(userId), new NetworkObserver<ApiResult<UserTeam>>() {

            @Override
            public void onReady(ApiResult<UserTeam> userTeamApiResult) {
                if (userTeamApiResult.getData() != null) {
                    level.setText("L" + userTeamApiResult.getData().getLevel());
                    mNumTv.setText(userTeamApiResult.getData().getTotalChildUser() + "");
                    tvJifen.setText(userTeamApiResult.getData().getTotalAchievement() + "");
                    if (userTeamApiResult.getData().getUserVo() != null) {
                        userVoBeans.addAll(userTeamApiResult.getData().getUserVo());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private void initView() {
        tvTitle.setText("我的团队");
    }

    private void init() {
        adapter = new TeamAdapter(this, R.layout.item_team, userVoBeans);
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
