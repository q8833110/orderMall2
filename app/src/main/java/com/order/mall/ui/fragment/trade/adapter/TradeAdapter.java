package com.order.mall.ui.fragment.trade.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TradeAdapter extends CommonAdapter<TradeAdapter.Order> {

    public TradeAdapter(Context context, int layoutId, List<Order> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Order order, int position) {

    }

    public static class Order{
        public Order(){}
    }
}
