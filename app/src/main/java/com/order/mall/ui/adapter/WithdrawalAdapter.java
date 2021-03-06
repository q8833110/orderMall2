package com.order.mall.ui.adapter;

import android.content.Context;
import android.widget.RadioButton;

import com.order.mall.R;
import com.order.mall.data.network.user.AlipayList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class WithdrawalAdapter extends CommonAdapter<AlipayList> {

    public WithdrawalAdapter(Context context, int layoutId, List<AlipayList> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AlipayList data, int position) {
        holder.setText(R.id.radio_withdrawal_amount, data.getAliPayAccount() + " (" + data.getAccountRealName()+")");
        RadioButton radioButton = holder.getView(R.id.radio_withdrawal_amount);
        if (data.isCheck()) {
            radioButton.setSelected(true);
        } else {
            radioButton.setSelected(false);
        }
    }
}
