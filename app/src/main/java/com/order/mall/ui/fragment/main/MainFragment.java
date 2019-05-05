package com.order.mall.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.order.mall.R;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends LazyLoadFragment {

    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.iv)
    ImageView iv;
    Unbinder unbinder;
    private View rootView;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;

    }


    private void init() {
        iv.setImageResource(R.drawable.banner);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
