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
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IFinancialProductsApi;
import com.order.mall.data.network.financial.FinancialProduct;
import com.order.mall.data.network.financial.FinancialProductOrder;
import com.order.mall.data.network.financial.SellOrder;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.activity.DetailsActivity;
import com.order.mall.ui.activity.DetailsSellActivity;
import com.order.mall.ui.fragment.main.LazyLoadFragment;
import com.order.mall.ui.fragment.trade.adapter.SellAdapter;
import com.order.mall.ui.fragment.trade.adapter.TradeAdapter;
import com.order.mall.util.LoginUtils;
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

import static com.order.mall.ui.fragment.trade.QiangdanFragment.INTENT_KEY_PRODUCT;

public class SellFragment extends LazyLoadFragment {
    public static final String INTENT_KEY_ORDRE_ID = "INTENT_KEY_SELL_ORDER_ID";
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private View rootView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private SellAdapter tradeAdapter ;

    private IFinancialProductsApi api;
    private int page = 1;
    private List<SellOrder.DataBean>  orders ;
    private int userId ;
    public static SellFragment newInstance() {
        SellFragment fragment = new SellFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_qiangdan, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        if (LoginUtils.isLogin(getContext()))
        init();
        else
        toLogin();
        return rootView;
    }

    private void init(){
        userId = (int) SharedPreferencesHelp.getInstance(getContext()).getUser().getId();
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
        tradeAdapter = new SellAdapter(getContext() ,R.layout.item_sell ,orders);
        tradeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext() , DetailsSellActivity.class);
                intent.putExtra(INTENT_KEY_ORDRE_ID , orders.get(position).getId());
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

    private void referList(final int page, int pageSize  ) {
        addObserver(api.sellList(page, 10 ,userId ,3), new NetworkObserver<ApiResult<SellOrder>>() {

            @Override
            public void onReady(ApiResult<SellOrder> financialProductApiResult) {
                if (financialProductApiResult.getData() != null && financialProductApiResult.getData() != null
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
