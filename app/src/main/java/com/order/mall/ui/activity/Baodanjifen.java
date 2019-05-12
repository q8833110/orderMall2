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
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.RechargeCenter;
import com.order.mall.data.network.user.TradeBalanceList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.MainActivity;
import com.order.mall.ui.adapter.PayMentDetailAdapter;
import com.order.mall.ui.fragment.main.LazyLoadFragment;
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

public class Baodanjifen extends BaseActivity {
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
    @BindView(R.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R.id.ll_qiangdan)
    LinearLayout llQiangdan;
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

    Unbinder unbinder;
    private int pageNum = 1;
    private int pageSize = 10;
    private long userId;
    private PayMentDetailAdapter adapter;
    private IUserApi iUserApi;
    List<TradeBalanceList.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baodanjifen);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        int tradeBalance = getIntent().getIntExtra("tradeBalance", 0);
        tvJifen.setText(String.valueOf(tradeBalance));
        userId = SharedPreferencesHelp.getInstance(this).getUser().getId();

        init();
        getAll();
    }

    /**
     * 获取全部列表
     */
    private void getAll() {

        addObserver(iUserApi.getAllTradeBalanceDetailsList(pageNum, pageSize, userId), new NetworkObserver<ApiResult<TradeBalanceList>>() {
            @Override
            public void onReady(ApiResult<TradeBalanceList> tradeBalanceListApiResult) {
                if (tradeBalanceListApiResult.getData() != null && tradeBalanceListApiResult.getData().getData() != null
                        && tradeBalanceListApiResult.getData().getData().size() > 0) {
                    if (pageNum == 1) {
                        list.clear();
                    }
                    list.addAll(tradeBalanceListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });
    }

    private void init() {

        adapter = new PayMentDetailAdapter(this, R.layout.item_payment, list);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(Baodanjifen.this, JifenxiangqingActivity.class);
                intent.putExtra("id", list.get(position).getId());
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

    @OnClick(R.id.rl_shouzhi)
    public void details() {
        Intent intent = new Intent(this, JiFenDetails.class);
        startActivity(intent);
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    @OnClick(R.id.tv_jifen)
    public void toJifen() {
        Intent intent = new Intent(this, JiFenDetails.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_recharge)
    public void toRecharge() {
        //充值中心
        Intent intent = new Intent(this, RechargeCenterActivity.class);
        intent.putExtra("position", 1);
        startActivity(intent);
    }

    @OnClick(R.id.ll_qiangdan)
    public void toRecharge2() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("position", 2);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
