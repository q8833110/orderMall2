package com.order.mall.ui.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import lombok.Data;

public class BaodanjifenAdapter extends CommonAdapter<BaodanjifenAdapter.Payments> {


    public BaodanjifenAdapter(Context context, int layoutId, List<Payments> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Payments payments, int position) {

    }

    @Data
    public static class Payments{

    }
}
