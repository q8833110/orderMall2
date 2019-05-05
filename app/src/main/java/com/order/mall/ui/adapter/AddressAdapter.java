package com.order.mall.ui.adapter;

import android.content.Context;

import com.order.mall.data.network.user.UserDeliverAddress;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class AddressAdapter extends CommonAdapter<UserDeliverAddress> {

    public AddressAdapter(Context context, int layoutId, List<UserDeliverAddress> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, UserDeliverAddress data, int position) {


    }

}
