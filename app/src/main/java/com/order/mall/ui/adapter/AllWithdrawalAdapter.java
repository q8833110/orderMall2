package com.order.mall.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.data.network.user.TradeBalanceList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class AllWithdrawalAdapter extends CommonAdapter<TradeBalanceList.DataBean> {


    public AllWithdrawalAdapter(Context context, int layoutId, List<TradeBalanceList.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TradeBalanceList.DataBean data, int position) {
        TextView jifen = holder.getView(R.id.jifen);
        TextView shengyujifen = holder.getView(R.id.shengyujifen);
        TextView time = holder.getView(R.id.time);
        TextView title = holder.getView(R.id.title);

        jifen.setText("+" + data.getTradeValue() + "报单积分");
        shengyujifen.setText("余额：" + data.getBalanceAfterOperate() + "报单积分");
        time.setText(data.getCreateDate());
        if (data.getTradeType() == 0) {
            //充值
            title.setText("充值");
        } else if (data.getTradeType() == 1) {
            //抢单
            title.setText("消费-抢单");
        }
    }
}
