package com.order.mall.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.CashScoreList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 现金积分列表适配器
 */
public class CashScoreListAdapter extends CommonAdapter<CashScoreList.DataBean> {


    public CashScoreListAdapter(Context context, int layoutId, List<CashScoreList.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CashScoreList.DataBean data, int position) {

        holder.setText(R.id.jifen, "+" + data.getCashValue() / 100 + "现金积分");
        holder.setText(R.id.shengyujifen, "余额：" + data.getBalanceAfterOperate() / 100 + "现金积分");
        holder.setText(R.id.time, data.getCreateDate());
        TextView statusTv = holder.getView(R.id.tv_status);

        int cashType = data.getCashType();

        if (cashType == 0) {
            holder.setText(R.id.title, "发起提现");
            statusTv.setVisibility(View.VISIBLE);

        } else if (cashType == 1) {
            holder.setText(R.id.title, "发起转账");
            statusTv.setVisibility(View.VISIBLE);
        } else if (cashType == 2) {
            holder.setText(R.id.title, "转账审核通过");
            statusTv.setVisibility(View.VISIBLE);

        } else if (cashType == 3) {
            holder.setText(R.id.title, "转账审核失败");
            statusTv.setVisibility(View.GONE);
        } else if (cashType == 4) {
            holder.setText(R.id.title, "提现审核通过");
            statusTv.setVisibility(View.VISIBLE);

        } else if (cashType == 5) {
            holder.setText(R.id.title, "提现审核失败");
            statusTv.setVisibility(View.GONE);

        } else if (cashType == 6) {
            holder.setText(R.id.title, "奖金积分-转入");
            statusTv.setVisibility(View.VISIBLE);

        } else if (cashType == 7) {
            holder.setText(R.id.title, "现金积分收益-转入");
            statusTv.setVisibility(View.VISIBLE);

        }
    }

}
