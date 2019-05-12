package com.order.mall.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.order.mall.R;
import com.order.mall.data.network.shop.ShopGoods;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import lombok.Data;

public class MallAdapter extends CommonAdapter<ShopGoods.ShopGood> {

    private Context context ;
    public MallAdapter(Context context, int layoutId, List<ShopGoods.ShopGood> datas) {
        super(context, layoutId, datas);
        this.context = context ;
    }

    @Override
    protected void convert(ViewHolder holder, ShopGoods.ShopGood shopGood, int position) {
        ImageView iv  = holder.getView(R.id.iv_image);
        Glide.with(context).load(shopGood.getPicture()).into(iv);
        holder.setText(R.id.tv_title , shopGood.getName());
        holder.setText(R.id.tv_price , shopGood.getPrice() +"");
    }
}
