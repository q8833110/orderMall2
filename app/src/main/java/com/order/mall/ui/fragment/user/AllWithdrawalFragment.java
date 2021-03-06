package com.order.mall.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.mall.R;
import com.order.mall.ui.activity.ReportDetailActivity;
import com.order.mall.ui.adapter.AllWithdrawalAdapter;
import com.order.mall.ui.adapter.BaodanjifenAdapter;
import com.order.mall.ui.fragment.main.LazyLoadFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 提现中心
 */
public class AllWithdrawalFragment extends LazyLoadFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;

    private AllWithdrawalAdapter adapter;

    public static AllWithdrawalFragment newInstance() {
        AllWithdrawalFragment fragment = new AllWithdrawalFragment();
        return fragment;
    }

    private View rootView;


    @Override
    protected void loadData() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_grade, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
//        开始下拉
        initRecy();
        refresh.setEnableRefresh(true);//启用刷新
        refresh.setEnableLoadMore(true);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
    }

        private void initRecy(){
//            List<BaodanjifenAdapter.Payments> list = new ArrayList<>();
//            for (int i = 0; i < 5; i++) {
//                list.add(new BaodanjifenAdapter.Payments());
//            }
//            adapter = new AllWithdrawalAdapter(getContext(), R.layout.item_recharge, list);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Intent intent = new Intent(getContext() ,ReportDetailActivity.class);
                    startActivity(intent);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            rv.setAdapter(adapter);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
        }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
