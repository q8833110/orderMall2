package com.order.mall.ui.activity.consume;

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
import com.order.mall.data.network.user.CashScoreList;
import com.order.mall.data.network.user.ConsumeList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.MainActivity;
import com.order.mall.ui.activity.Jifenxiangqing2Activity;
import com.order.mall.ui.activity.bonus.BonusJifenDetailsActivity;
import com.order.mall.ui.activity.cash.TransferJifenActivity;
import com.order.mall.ui.adapter.ConsumeScoreListAdapter;
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

public class ConsumeMainActivity extends BaseActivity {

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

    private ConsumeScoreListAdapter adapter;
    private IUserApi iUserApi;
    private int pageNum = 1;
    private int pageSize = 10;
    private long userId ;
    private List<ConsumeList.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consuem);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        int consumeBalance = getIntent().getIntExtra("consumeBalance", 0);
        tvJifen.setText(consumeBalance + "");
        userId = SharedPreferencesHelp.getInstance(this).getUser().getId();

        init();
        getAll();
    }

    private void getAll() {
        addObserver(iUserApi.consumeBalanceDetailsListAll(pageNum, pageSize, userId), new NetworkObserver<ApiResult<ConsumeList>>() {

            @Override
            public void onReady(ApiResult<ConsumeList> consumeListApiResult) {
                if (consumeListApiResult.getData() != null && consumeListApiResult.getData().getData() != null) {
                    if (pageNum == 1) {
                        dataBeans.clear();
                    }
                    dataBeans.addAll(consumeListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });


    }

    private void init() {

        adapter = new ConsumeScoreListAdapter(this, R.layout.item_payment, dataBeans);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(ConsumeMainActivity.this, Jifenxiangqing2Activity.class);
                intent.putExtra("id", dataBeans.get(position).getId());
                intent.putExtra("type",2);
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
        Intent intent = new Intent(this, ConsumeJifenDetailsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_transfer)
    public void toBuy() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("position", 3);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
