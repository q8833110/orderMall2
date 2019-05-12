package com.order.mall.ui.activity.bonus;

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
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.BounsScoreList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.JiFenDetails;
import com.order.mall.ui.activity.Jifenxiangqing2Activity;
import com.order.mall.ui.activity.RechargeCenterActivity;
import com.order.mall.ui.activity.ReportDetailActivity;
import com.order.mall.ui.activity.cash.TransferJifenActivity;
import com.order.mall.ui.adapter.BonusScoreListAdapter;
import com.order.mall.ui.adapter.PayMentDetailAdapter;
import com.order.mall.util.RetrofitUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.http.PATCH;

public class BonusMainActivity extends BaseActivity {

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
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    private BonusScoreListAdapter adapter;
    private IUserApi iUserApi;
    private int pageNum = 1;
    private int pageSize = 10;
    private long userId = 500000;
    private List<BounsScoreList.DataBean> dataBeans = new ArrayList<>();
    private int bonusBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        bonusBalance = getIntent().getIntExtra("bonusBalance", 0);
        tvJifen.setText(bonusBalance + "");
        init();
        getAll();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAll();
    }

    private void getAll() {
        addObserver(iUserApi.bonusBalanceDetailsListAll(pageNum, pageSize, userId), new NetworkObserver<ApiResult<BounsScoreList>>() {

            @Override
            public void onReady(ApiResult<BounsScoreList> bounsScoreListApiResult) {
                if (bounsScoreListApiResult.getData() != null && bounsScoreListApiResult.getData().getData() != null) {
                    if (pageNum == 1) {
                        dataBeans.clear();
                    }
                    dataBeans.addAll(bounsScoreListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });
    }


    private void init() {

        adapter = new BonusScoreListAdapter(this, R.layout.item_payment, dataBeans);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(BonusMainActivity.this, Jifenxiangqing2Activity.class);
                intent.putExtra("id",dataBeans.get(position).getId());
                intent.putExtra("type",1);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum = 1;
                getAll();
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                getAll();
            }
        });
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    @OnClick(R.id.rl_shouzhi)
    public void toMingxi() {
        Intent intent = new Intent(this, BonusJifenDetailsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_transfer)
    public void toRecharge() {
        Intent intent = new Intent(this, TransferJifenActivity.class);
        intent.putExtra("type", 2);
        intent.putExtra("num", bonusBalance);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
