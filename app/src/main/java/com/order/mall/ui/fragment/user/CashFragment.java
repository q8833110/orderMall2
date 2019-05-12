package com.order.mall.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.CashScoreList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.activity.cash.CashWithdrawalDetailsActivity;
import com.order.mall.ui.adapter.CashScoreListAdapter;
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
import butterknife.Unbinder;

public class CashFragment extends LazyLoadFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;

    private CashScoreListAdapter adapter;
    private IUserApi iUserApi;
    private int pageNum = 1;
    private int pageSize = 10;
    private long userId ;
    private int type = 0;

    private List<CashScoreList.DataBean> dataBeans = new ArrayList<>();

    public static CashFragment newInstance(int type) {
        CashFragment fragment = new CashFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    private View rootView;


    @Override
    protected void loadData() {
        if (type == -1) {
            getAll();
        } else {
            getList();
        }
    }

    private void getAll() {
        addObserver(iUserApi.cashBalanceDetailsListAll(pageNum, pageSize, userId), new NetworkObserver<ApiResult<CashScoreList>>() {

            @Override
            public void onReady(ApiResult<CashScoreList> cashListApiResult) {
                if (cashListApiResult.getData() != null && cashListApiResult.getData().getData() != null) {
                    if (pageNum == 1) {
                        dataBeans.clear();
                    }
                    dataBeans.addAll(cashListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });


    }

    private void getList() {

        addObserver(iUserApi.cashBalanceDetailsList(pageNum, pageSize, userId, type), new NetworkObserver<ApiResult<CashScoreList>>() {

            @Override
            public void onReady(ApiResult<CashScoreList> cashListApiResult) {
                if (cashListApiResult.getData() != null && cashListApiResult.getData().getData() != null) {
                    if (pageNum == 1) {
                        dataBeans.clear();
                    }
                    dataBeans.addAll(cashListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_grade, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        type = getArguments().getInt("type");
        userId = SharedPreferencesHelp.getInstance(getContext()).getUser().getId();

        init();
        return rootView;
    }

    private void init() {
//        开始下拉
        initRecy();
        refresh.setEnableRefresh(true);//启用刷新
        refresh.setEnableLoadMore(true);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum = 1;
                loadData();
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                loadData();
            }
        });
    }

    private void initRecy() {
        adapter = new CashScoreListAdapter(getContext(), R.layout.item_cash, dataBeans);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext(), CashWithdrawalDetailsActivity.class);
                intent.putExtra("id", dataBeans.get(position).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
