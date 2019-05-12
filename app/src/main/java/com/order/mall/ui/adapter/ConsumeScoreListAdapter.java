package com.order.mall.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.CashScoreList;
import com.order.mall.data.network.user.ConsumeList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 现金积分列表适配器
 */
public class ConsumeScoreListAdapter extends CommonAdapter<ConsumeList.DataBean> {


    public ConsumeScoreListAdapter(Context context, int layoutId, List<ConsumeList.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ConsumeList.DataBean data, int position) {

        holder.setText(R.id.shengyujifen, "余额：" + data.getBalanceAfterOperate() / 100 + "消费积分");
        holder.setText(R.id.time, data.getCreateDate());

        int cashType = data.getConsumeType();

        if (cashType == 0) {
            holder.setText(R.id.title, "购物");
            holder.setText(R.id.jifen, "-" + data.getConsumeValue() / 100 + "现金积分");

        } else if (cashType == 1) {
            holder.setText(R.id.title, "转入-报单分红");
            holder.setText(R.id.jifen, "+" + data.getConsumeValue() / 100 + "现金积分");

        }
    }

}
