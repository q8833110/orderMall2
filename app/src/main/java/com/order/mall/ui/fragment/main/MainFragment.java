package com.order.mall.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.order.mall.R;
import com.order.mall.data.network.IHomePage;
import com.order.mall.data.network.IShopApi;
import com.order.mall.data.network.homepage.Home;
import com.order.mall.data.network.shop.ShopGoods;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.LoginActivity;
import com.order.mall.ui.NoticeActivity;
import com.order.mall.ui.activity.DetailsMallActivity;
import com.order.mall.ui.activity.RechargeActivity;
import com.order.mall.ui.activity.user.InvitationActivity;
import com.order.mall.util.GlideImageLoader;
import com.order.mall.util.LoginUtils;
import com.order.mall.util.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.order.mall.ui.fragment.main.MallFragment.INTENT_KEY_SHOP_ID;


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
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    private View rootView;

    private IHomePage homePage;

    private IShopApi api ;

    private CommonAdapter<ShopGoods.ShopGood> adapter ;
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

    @OnClick(R.id.iv_message)
    public void message(){
        startActivity(new Intent(getContext() , NoticeActivity.class));
    }

    private Listener listener ;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public Listener getListener() {
        return listener;
    }

    @OnClick(R.id.ll_qiangdan)
    public void toQiangdan(){
        if (listener != null)
        listener.toQiangdan();
    }

    @OnClick(R.id.ll_jifen)
    public void toRecharge(){
        if (LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), RechargeActivity.class));
        }else{
            startActivity(new Intent(getContext() , LoginActivity.class));
        }
    }

    public interface Listener{
        void toQiangdan();
    }
    List<ShopGoods.ShopGood> shopGoods ;
    private void init() {
        api = RetrofitUtils.getInstance().getRetrofit().create(IShopApi.class);
        List<String> urls = new ArrayList<>();
        urls.add("http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905082051280690181601.jpg");
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
//        banner.setImages(urls);
//        banner.start();
        homePage = RetrofitUtils.getInstance().getRetrofit().create(IHomePage.class);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        search(textView.getText().toString());
                        break;
                }
                return false ;
            }
        });
        initRecy();
    }

    private void initRecy() {
        shopGoods = new ArrayList<>();
        adapter = new CommonAdapter<ShopGoods.ShopGood>(getContext() , R.layout.item_shop , shopGoods){

            @Override
            protected void convert(ViewHolder holder, ShopGoods.ShopGood shopGood, int position) {
                holder.setText(R.id.tv_name ,shopGood.getName());
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext() , DetailsMallActivity.class);
                intent.putExtra(INTENT_KEY_SHOP_ID , shopGoods.get(position).getId());
                startActivity(intent);
                hideRvShop();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rvShop.setAdapter(adapter);
        rvShop.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void search(String name){
        addObserver(api.shopList(1 , 20 , name) , new NetworkObserver<ApiResult<ShopGoods>>(){

            @Override
            public void onReady(ApiResult<ShopGoods> shopGoodsApiResult) {
                    if (shopGoodsApiResult.getData() != null){
                        if (shopGoodsApiResult.getData().getData() != null &&
                                shopGoodsApiResult.getData().getData().size() > 0){
                                showRvShop();
                                shopGoods.clear();
                                shopGoods.addAll(shopGoodsApiResult.getData().getData());
                                adapter.notifyDataSetChanged();

                        }else{
                            hideRvShop();
                            Toast.makeText(getContext() , "没有数据",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        hideRvShop();
                        Toast.makeText(getContext() , shopGoodsApiResult.getMessage() ,Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void showRvShop(){
        rvShop.setVisibility(View.VISIBLE);
    }

    private void hideRvShop(){
        rvShop.setVisibility(View.GONE);
    }

    @Override
    protected void loadData() {
        addObserver(homePage.homePage(), new NetworkObserver<ApiResult<Home>>() {
            @Override
            public void onReady(ApiResult<Home> homeApiResult) {
                if (homeApiResult.getData() != null) {
                    banner.setImages(homeApiResult.getData().getBanner());
                    tvMall.setText(homeApiResult.getData().getScoreScDetails());
                    tvProcdct.setText(homeApiResult.getData().getProductScDetails());
                    banner.start();
                }
            }
        });
    }

    @OnClick(R.id.ll_yaoqing)
    public void toYaoqing(){
        if (LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), InvitationActivity.class));
        }else{
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
