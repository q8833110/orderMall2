package com.order.mall.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.BounsScoreList;
import com.order.mall.data.network.user.CashList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 现金积分列表适配器
 */
public class BonusScoreListAdapter extends CommonAdapter<BounsScoreList.DataBean> {


    public BonusScoreListAdapter(Context context, int layoutId, List<BounsScoreList.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, BounsScoreList.DataBean data, int position) {

        holder.setText(R.id.jifen, "+" + data.getBonusValue() / 100 + "奖金积分");
        holder.setText(R.id.shengyujifen, "余额：" + data.getBalanceAfterOperate() / 100 + "奖金积分");
        holder.setText(R.id.time, data.getCreateDate());

        int bonusType = data.getBonusType();

        if (bonusType == 0) {
            holder.setText(R.id.title, "转入-奖金积分");
        } else if (bonusType == 1) {
            holder.setText(R.id.title, "转出-奖金积分");
        }
    }

}
