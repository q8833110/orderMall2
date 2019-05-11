package com.order.mall.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.order.mall.R;
import com.order.mall.ui.activity.DetailsMallActivity;
import com.order.mall.ui.adapter.MallAdapter;
import com.order.mall.util.ScreenUtils;
import com.order.mall.widget.DividerGridItemDecoration;
import com.order.mall.widget.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MallFragment extends LazyLoadFragment {

    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private View rootView;

    MallAdapter mallAdapter;

    public static MallFragment newInstance() {
        MallFragment fragment = new MallFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mall, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        List<MallAdapter.Mall> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new MallAdapter.Mall());
        }
        mallAdapter = new MallAdapter(getContext(), R.layout.item_mall, list);
        mallAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext() , DetailsMallActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(mallAdapter);
        rv.addItemDecoration(new SpaceItemDecoration(ScreenUtils.dpToPxInt(getContext() , 5)));
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected void loadData() {
        addNet();
    }

    private void addNet(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
