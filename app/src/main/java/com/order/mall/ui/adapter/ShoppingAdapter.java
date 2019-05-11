package com.order.mall.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.order.mall.R;
import com.order.mall.data.network.user.ShoppingList;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ShoppingAdapter extends CommonAdapter<ShoppingList.DataBean> {

    private Context context;

    public ShoppingAdapter(Context context, int layoutId, List<ShoppingList.DataBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, ShoppingList.DataBean data, int position) {
        TextView time = holder.getView(R.id.time);
        TextView status = holder.getView(R.id.status);
        ImageView image = holder.getView(R.id.image);
        TextView title = holder.getView(R.id.title);
        TextView tv_price = holder.getView(R.id.tv_price);
        TextView tv_num = holder.getView(R.id.tv_num);
        TextView total = holder.getView(R.id.total);
        TextView delete = holder.getView(R.id.delete);
        View line1 = holder.getView(R.id.line1);

        time.setText(data.getPayTime());
        Glide.with(context).load(data.getPicture()).into(image);
        title.setText(data.getName());
        tv_price.setText(data.getPrice() + "");
        tv_num.setText("x" + data.getNum());
        total.setText("共" + data.getNum() + "件，共付总额：" + data.getPrice() + "消费积分");
        if (data.getStatus() == 1) {
            //代发货
            status.setText("代发货");
            status.setTextColor(context.getResources().getColor(R.color.colorFullRed));
            delete.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
        } else if (data.getStatus() == 2) {
            status.setText("待收货");
            status.setTextColor(context.getResources().getColor(R.color.colorFullRed));
            delete.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
        } else if (data.getStatus() == 3) {
            status.setText("已收货");
            status.setTextColor(context.getResources().getColor(R.color.colorBlank333333));
            delete.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
        }
    }

}
