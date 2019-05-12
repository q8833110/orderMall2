package com.order.mall.ui.adapter;

import android.content.Context;

import com.order.mall.R;
import com.order.mall.data.network.user.AlipayList;
import com.order.mall.data.network.user.WeixinList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class WeixinMethodAdapter extends CommonAdapter<WeixinList> {

    public WeixinMethodAdapter(Context context, int layoutId, List<WeixinList> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, WeixinList data, int position) {
        holder.setText(R.id.tv_content, data.getWeixinPayAccount());
    }

}
