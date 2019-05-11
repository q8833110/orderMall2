package com.order.mall.ui.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.order.mall.R;
import com.order.mall.data.network.IHomePage;
import com.order.mall.data.network.homepage.Home;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.GlideImageLoader;
import com.order.mall.util.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

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

    Unbinder unbinder;
    @BindView(R.id.ll_jifen)
    LinearLayout llJifen;
    @BindView(R.id.ll_qiangdan)
    LinearLayout llQiangdan;
    @BindView(R.id.ll_yaoqing)
    LinearLayout llYaoqing;
    @BindView(R.id.tv_mall)
    TextView tvMall;
    @BindView(R.id.tv_procdct)
    TextView tvProcdct;
    private View rootView;

    private IHomePage homePage;

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
        loadData();
        return rootView;

    }

    private void init() {
        List<String> urls = new ArrayList<>();
        urls.add("http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905082051280690181601.jpg");
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器
        banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
        banner.setImages(urls);
        banner.start();
        homePage = RetrofitUtils.getInstance().getRetrofit().create(IHomePage.class);
    }

    @Override
    protected void loadData() {
        addObserver(homePage.homePage(), new NetworkObserver<ApiResult<Home>>(){
            @Override
            public void onReady(ApiResult<Home> homeApiResult) {
                if (homeApiResult.getData() != null){
                    banner.setImages(homeApiResult.getData().getBanner());
                    tvMall.setText(homeApiResult.getData().getScoreScDetails());
                    tvProcdct.setText(homeApiResult.getData().getProductScDetails());
                    banner.start();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
