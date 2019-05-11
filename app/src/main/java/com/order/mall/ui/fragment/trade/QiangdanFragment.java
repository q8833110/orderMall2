package com.order.mall.ui.fragment.trade;

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
import com.order.mall.data.network.IFinancialProductsApi;
import com.order.mall.data.network.financial.FinancialProduct;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.activity.DetailsActivity;
import com.order.mall.ui.fragment.main.LazyLoadFragment;
import com.order.mall.ui.fragment.trade.adapter.TradeAdapter;
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

public class QiangdanFragment extends LazyLoadFragment {

    public static final String INTENT_KEY_PRODUCT = "INTENT_KEY_PRODUCT" ;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private View rootView;

    private TradeAdapter tradeAdapter;

    private List<FinancialProduct.DataBean> orders;

    public static QiangdanFragment newInstance() {
        QiangdanFragment fragment = new QiangdanFragment();
        return fragment;
    }

    private IFinancialProductsApi api;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_qiangdan, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                page = 1;
                referList(page , 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                page++ ;
                referList(page , 10);
            }
        });
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        api = RetrofitUtils.getInstance().getRetrofit().create(IFinancialProductsApi.class);
        orders = new ArrayList<>();
        tradeAdapter = new TradeAdapter(getContext(), R.layout.item_qiangdan, orders);
        tradeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(INTENT_KEY_PRODUCT , orders.get(position));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(tradeAdapter);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void loadData() {
        if (refreshLayout != null)
        refreshLayout.autoRefresh();
    }

    private void referList(final int page, int pageSize) {
        addObserver(api.listfinancialProducts(page, 10), new NetworkObserver<ApiResult<FinancialProduct>>() {

            @Override
            public void onReady(ApiResult<FinancialProduct> financialProductApiResult) {
                if (financialProductApiResult.getData() != null && financialProductApiResult.getData().getData() != null
                        && financialProductApiResult.getData().getData().size() > 0) {
                    if (page == 1) {
                        orders.clear();
                    }
                    orders.addAll(financialProductApiResult.getData().getData());
                    tradeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
