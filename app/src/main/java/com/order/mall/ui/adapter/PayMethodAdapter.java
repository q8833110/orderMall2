package com.order.mall.ui.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class PayMethodAdapter extends CommonAdapter<PayMethodAdapter.Data> {

    public PayMethodAdapter(Context context, int layoutId, List<Data> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Data data, int position) {

    }

    public static class Data{

    }
}
