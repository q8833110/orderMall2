package com.order.mall.ui.fragment.trade.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.order.mall.R;
import com.order.mall.data.network.financial.FinancialProduct;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TradeAdapter extends CommonAdapter<FinancialProduct.DataBean> {
    private Context context ;
    public TradeAdapter(Context context, int layoutId, List<FinancialProduct.DataBean> datas) {
        super(context, layoutId, datas);
        this.context  = context ;
    }

    @Override
    protected void convert(ViewHolder holder, FinancialProduct.DataBean order, int position) {
        ImageView iv=  holder.getView(R.id.iv_image);
//        Glide.with(context).load(order.getImages()).into(iv);
//        holder.setText(R.id.tv_title ,order.getTitle());
//        holder.setText(R.id.tv_price ,order.getTradeScore()+"");
//        holder.setText(R.id.time ,order.getEndTimeMs());
    }
}
