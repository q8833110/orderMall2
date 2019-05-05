package com.order.mall.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.order.mall.ui.fragment.main.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

public class TextAdapter extends FragmentPagerAdapter {
    String[] titles;
    List<Fragment> lists = new ArrayList<>();

    public TextAdapter(FragmentManager fm, String[] titles, List<Fragment> list) {
        super(fm);
        this.titles = titles;
        this.lists = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists == null ? 0 :lists.size();
    }
}