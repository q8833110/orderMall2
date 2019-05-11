package com.order.mall.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.order.mall.R;
import com.order.mall.data.network.user.TradeOrderList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TradeAdapter extends CommonAdapter<TradeOrderList.DataBean> {

    private Context context;

    public TradeAdapter(Context context, int layoutId, List<TradeOrderList.DataBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, TradeOrderList.DataBean data, int position) {
        TextView mIdTv = holder.getView(R.id.id);
        TextView title = holder.getView(R.id.title);
        ImageView image = holder.getView(R.id.image);
        TextView tv_rate = holder.getView(R.id.tv_rate);
        TextView jifen = holder.getView(R.id.jifen);
        TextView status = holder.getView(R.id.status);
        TextView time = holder.getView(R.id.time);

        mIdTv.setText("订单编号：" + data.getId());
        title.setText(data.getTitle());
        Glide.with(context).load(data.getImages()).into(image);
        tv_rate.setText(data.getAnnualizedRate() + "");
        jifen.setText(data.getTradeScore() + "");
        time.setText("挂卖时间：" + data.getCreateTime());
        if (data.getStatus() == 1) {
            status.setText("已抢购");
        } else if (data.getStatus() == 2) {
            status.setText("已到期");
        } else if (data.getStatus() == 3) {
            status.setText("已挂卖");
        } else if (data.getStatus() == 4) {
            status.setText("已收益");
        }

    }
}
