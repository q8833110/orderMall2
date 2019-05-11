package com.order.mall.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.CashList;
import com.order.mall.data.network.user.RechargeCenter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


public class CashCenterAdapter extends CommonAdapter<CashList.DataBean> {

    private Context context;

    public CashCenterAdapter(Context context, int layoutId, List<CashList.DataBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, CashList.DataBean dataBean, int position) {
        TextView mOrderTv = holder.getView(R.id.order);
        mOrderTv.setText("订单号：" + dataBean.getId());
        TextView mPriceTv = holder.getView(R.id.price);
        mPriceTv.setText("提现数量：" + (dataBean.getEncashValue() / 100) + "现金积分");
        TextView mTimeTv = holder.getView(R.id.time);
        mTimeTv.setText("下单时间：" + dataBean.getCreateDate());
        TextView mStatusTv = holder.getView(R.id.status);
        if (dataBean.getEncashStatus() == 0) {
            mStatusTv.setText("节点审核中");
            mStatusTv.setTextColor(context.getResources().getColor(R.color.colorFullRed));
        } else if (dataBean.getEncashStatus() == 1) {
            mStatusTv.setTextColor(context.getResources().getColor(R.color.color00AD66));
            mStatusTv.setText("提现完成");
        } else if (dataBean.getEncashStatus() == 2) {
            mStatusTv.setTextColor(context.getResources().getColor(R.color.color999999));
            mStatusTv.setText("提现失败");
        }
    }

}
