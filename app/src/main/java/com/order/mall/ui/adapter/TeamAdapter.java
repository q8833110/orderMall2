package com.order.mall.ui.adapter;

import android.content.Context;

import com.order.mall.data.network.user.UserTeam;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TeamAdapter extends CommonAdapter<UserTeam.UserVoBean> {

    public TeamAdapter(Context context, int layoutId, List<UserTeam.UserVoBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, UserTeam.UserVoBean userVoBean, int position) {

    }

}
