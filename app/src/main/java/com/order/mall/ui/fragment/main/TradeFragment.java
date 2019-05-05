package com.order.mall.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kcrason.dynamicpagerindicatorlibrary.DynamicPagerIndicator;
import com.library.tabstrip.PagerSlidingTabStrip;
import com.order.mall.R;
import com.order.mall.ui.adapter.TextAdapter;
import com.order.mall.ui.fragment.trade.QiangdanFragment;
import com.order.mall.ui.fragment.trade.SellFragment;
import com.order.mall.ui.fragment.trade.adapter.TradeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TradeFragment extends LazyLoadFragment {

    @BindView(R.id.pager)
    ViewPager pager;
    Unbinder unbinder;
    @BindView(R.id.dynamic_pager_indicator1)
    DynamicPagerIndicator dynamicPagerIndicator1;
    private View rootView;

    private List<Fragment> list;

    private String[] titles = new String[]{
            "抢单", "卖出"
    };

    public static TradeFragment newInstance() {
        TradeFragment fragment = new TradeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_trade, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        // 设置Tab底部选中的指示器 Indicator的颜色
        list = new ArrayList<>();
        list.add(QiangdanFragment.newInstance());
        list.add(SellFragment.newInstance());
        pager.setAdapter(new TextAdapter(getChildFragmentManager(), titles, list));
        dynamicPagerIndicator1.setViewPager(pager);
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
