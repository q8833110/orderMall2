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
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.BounsScoreList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.activity.Jifenxiangqing2Activity;
import com.order.mall.ui.adapter.BonusScoreListAdapter;
import com.order.mall.ui.fragment.main.LazyLoadFragment;
import com.order.mall.util.RetrofitUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AllBonusFragment extends LazyLoadFragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;

    private BonusScoreListAdapter adapter;
    private IUserApi iUserApi;

    public static AllBonusFragment newInstance(int type) {
        AllBonusFragment fragment = new AllBonusFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);

        return fragment;
    }

    private View rootView;

    private int pageNum = 1;
    private int pageSize = 10;
    private long userId ;
    private int type = 0;
    List<BounsScoreList.DataBean> list = new ArrayList<>();

    @Override
    protected void loadData() {
        if (type == -1) {
            getAll();
        } else {
            getList();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_grade, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        type = getArguments().getInt("type");
        userId = SharedPreferencesHelp.getInstance(getContext()).getUser().getId();

        init();
        return rootView;
    }

    /**
     * 获取全部列表
     */
    private void getAll() {

        addObserver(iUserApi.bonusBalanceDetailsListAll(pageNum, pageSize, userId), new NetworkObserver<ApiResult<BounsScoreList>>() {
            @Override
            public void onReady(ApiResult<BounsScoreList> bounsScoreListApiResult) {
                if (bounsScoreListApiResult.getData() != null && bounsScoreListApiResult.getData().getData() != null
                        && bounsScoreListApiResult.getData().getData().size() > 0) {
                    if (pageNum == 1) {
                        list.clear();
                    }
                    list.addAll(bounsScoreListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });
    }

    /**
     * 获取列表数据
     */
    private void getList() {

        addObserver(iUserApi.bonusBalanceDetailsList(pageNum, pageSize, userId, type), new NetworkObserver<ApiResult<BounsScoreList>>() {
            @Override
            public void onReady(ApiResult<BounsScoreList> bounsScoreListApiResult) {
                if (bounsScoreListApiResult.getData() != null && bounsScoreListApiResult.getData().getData() != null
                        && bounsScoreListApiResult.getData().getData().size() > 0) {
                    if (pageNum == 1) {
                        list.clear();
                    }
                    list.addAll(bounsScoreListApiResult.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                refresh.finishRefresh();
                refresh.finishLoadMore();
            }
        });

    }

    private void init() {
//        开始下拉
        initRecy();
        refresh.setEnableRefresh(true);//启用刷新
        refresh.setEnableLoadMore(true);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum = 1;
                loadData();
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                loadData();
            }
        });
    }


    private void initRecy() {
        adapter = new BonusScoreListAdapter(getContext(), R.layout.item_payment1, list);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext(), Jifenxiangqing2Activity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("type",1);
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
