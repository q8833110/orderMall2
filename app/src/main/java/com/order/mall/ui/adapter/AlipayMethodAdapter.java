package com.order.mall.ui.adapter;

import android.content.Context;

import com.order.mall.R;
import com.order.mall.data.network.user.AlipayList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class AlipayMethodAdapter extends CommonAdapter<AlipayList> {

    public AlipayMethodAdapter(Context context, int layoutId, List<AlipayList> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AlipayList data, int position) {
        holder.setText(R.id.tv_content,data.getAliPayAccount()+" ("+data.getAccountRealName()+")");
    }

}
