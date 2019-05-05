package com.order.mall.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import lombok.Data;

public class MallAdapter extends CommonAdapter<MallAdapter.Mall> {
    public MallAdapter(Context context, int layoutId, List<Mall> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Mall mall, int position) {

    }

    @Data
    public static class Mall{

    }
}
