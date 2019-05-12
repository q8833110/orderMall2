package com.order.mall.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class AddressAdapter extends CommonAdapter<UserDeliverAddress> {

    private AddressListener listener ;

    public AddressAdapter(Context context, int layoutId, List<UserDeliverAddress> datas) {
        super(context, layoutId, datas);
    }

    public void setListener(AddressListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(ViewHolder holder, UserDeliverAddress data, final int position) {
        holder.setText(R.id.name ,data.getReciver());
        holder.setText(R.id.mobile ,data.getMobile());
        holder.setText(R.id.address , data.getAddress());
        RadioButton radioButton = holder.getView(R.id.radius);
        if (data.isIsDefault()){
            radioButton.setChecked(true);
        }else
             radioButton.setChecked(false);

        holder.setOnClickListener(R.id.edit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.edit(position);
            }
        });

        holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.delete(position);
            }
        });

        holder.setOnClickListener(R.id.radius, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton radioButton1 = holder.getView(R.id.radius);
                if (listener != null) listener.edit(position , radioButton.isChecked() );
            }
        });

        if (data.isIsDefault()){
            SharedPreferencesHelp.getInstance(mContext).putAddress(data);
        }

    }


    public interface AddressListener{

        void delete(int position );

        void edit(int position);

        void edit(int position , boolean isCheck);
    }

}
