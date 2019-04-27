package com.order.mall.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.mall.R;

public abstract class BaseFragment extends Fragment {

    protected final  String TAG = this.getClass().getSimpleName();
    public  Context context ;
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context ;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity ;
    }

    public View view ;

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null ;
    }

    public abstract void init();

    public abstract void initView(View view);
}
