package com.order.mall.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.RechargeCenter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


public class RechargeCenterAdapter extends CommonAdapter<RechargeCenter.DataBean> {


    public RechargeCenterAdapter(Context context, int layoutId, List<RechargeCenter.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, RechargeCenter.DataBean dataBean, int position) {
        TextView mOrderTv = holder.getView(R.id.order);
        mOrderTv.setText("订单号：" + dataBean.getId());
        TextView mPriceTv = holder.getView(R.id.price);
        mPriceTv.setText("充值金额：" + dataBean.getAmount());
        TextView mTimeTv = holder.getView(R.id.time);
        mTimeTv.setText("下单时间：" + dataBean.getCreateTime());
        TextView mStatusTv = holder.getView(R.id.status);
        if (dataBean.getStatus() == 1) {
            mStatusTv.setText("充值中");
        } else if (dataBean.getStatus() == 2) {
            mStatusTv.setText("充值成功");
        } else if (dataBean.getStatus() == 3) {
            mStatusTv.setText("充值失败");
        }
    }

}
