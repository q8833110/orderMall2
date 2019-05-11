package com.order.mall.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.TradeBalanceList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import lombok.Data;

public class PayMentDetailAdapter extends CommonAdapter<TradeBalanceList.DataBean> {


    public PayMentDetailAdapter(Context context, int layoutId, List<TradeBalanceList.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TradeBalanceList.DataBean data, int position) {

        holder.setText(R.id.jifen, "+" + data.getTradeValue() + "报单积分");
        holder.setText(R.id.shengyujifen, "余额：" + data.getBalanceAfterOperate() + "报单积分");
        holder.setText(R.id.time, data.getCreateDate());

        if (data.getTradeType() == 0) {
            //充值
            holder.setText(R.id.title, "充值");
        } else if (data.getTradeType() == 1) {
            //抢单
            holder.setText(R.id.title, "消费-抢单");
        }
    }

}
