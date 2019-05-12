package com.order.mall.ui.adapter;

import android.content.Context;

import com.order.mall.R;
import com.order.mall.data.network.user.AlipayList;
import com.order.mall.data.network.user.BankList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class PayMethodAdapter extends CommonAdapter<BankList> {

    public PayMethodAdapter(Context context, int layoutId, List<BankList> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, BankList data, int position) {
        holder.setText(R.id.tv_content,data.getBankNo()+"  "+data.getAccountName());
    }

}
