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
import com.order.mall.data.network.financial.FinancialProduct;
import com.order.mall.ui.activity.DetailsActivity;
import com.order.mall.ui.activity.DetailsSellActivity;
import com.order.mall.ui.fragment.main.LazyLoadFragment;
import com.order.mall.ui.fragment.trade.adapter.TradeAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SellFragment extends LazyLoadFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private View rootView;

    private TradeAdapter tradeAdapter ;

    private List<FinancialProduct.DataBean>  orders ;
    public static SellFragment newInstance() {
        SellFragment fragment = new SellFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_qiangdan, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init(){
        orders = new ArrayList<>();
        tradeAdapter = new TradeAdapter(getContext() ,R.layout.item_sell ,orders);
        tradeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext() , DetailsSellActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(tradeAdapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
