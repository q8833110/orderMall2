package com.order.mall.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.order.mall.R;
import com.order.mall.data.network.IShopApi;
import com.order.mall.data.network.shop.ShopGoods;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.activity.DetailsMallActivity;
import com.order.mall.ui.adapter.MallAdapter;
import com.order.mall.util.RetrofitUtils;
import com.order.mall.util.ScreenUtils;
import com.order.mall.widget.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MallFragment extends LazyLoadFragment {

    public static final String INTENT_KEY_SHOP_ID = "INTENT_KEY_SHOP_ID";
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    private View rootView;

    MallAdapter mallAdapter;

    private IShopApi shopApi;

    List<ShopGoods.ShopGood> shopGoods;

    private int page;
    List<ShopGoods.ShopGood> shopGoods1 ;

    private CommonAdapter<ShopGoods.ShopGood> adapter ;
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
        shopApi = RetrofitUtils.getInstance().getRetrofit().create(IShopApi.class);
        shopGoods = new ArrayList<>();
        mallAdapter = new MallAdapter(getContext(), R.layout.item_mall, shopGoods);
        mallAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext(), DetailsMallActivity.class);
                intent.putExtra(INTENT_KEY_SHOP_ID, shopGoods.get(position).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(mallAdapter);
        rv.addItemDecoration(new SpaceItemDecoration(ScreenUtils.dpToPxInt(getContext(), 5)));
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
        initBanner();
        initRecy();
    }

    private void initRecy() {
        shopGoods1 = new ArrayList<>();
        adapter = new CommonAdapter<ShopGoods.ShopGood>(getContext(), R.layout.item_shop, shopGoods1) {

            @Override
            protected void convert(ViewHolder holder, ShopGoods.ShopGood shopGood, int position) {
                holder.setText(R.id.tv_name, shopGood.getName());
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getContext(), DetailsMallActivity.class);
                intent.putExtra(INTENT_KEY_SHOP_ID, shopGoods.get(position).getId());
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

    private void search(String name) {
        addObserver(shopApi.shopList(1, 20, name), new NetworkObserver<ApiResult<ShopGoods>>() {

            @Override
            public void onReady(ApiResult<ShopGoods> shopGoodsApiResult) {
                if (shopGoodsApiResult.getData() != null) {
                    if (shopGoodsApiResult.getData().getData() != null &&
                            shopGoodsApiResult.getData().getData().size() > 0) {
                        showRvShop();
                        shopGoods1.clear();
                        shopGoods1.addAll(shopGoodsApiResult.getData().getData());
                        adapter.notifyDataSetChanged();

                    } else {
                        hideRvShop();
                        Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    hideRvShop();
                    Toast.makeText(getContext(), shopGoodsApiResult.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showRvShop() {
        rvShop.setVisibility(View.VISIBLE);
    }

    private void hideRvShop() {
        rvShop.setVisibility(View.GONE);
    }

    private void initBanner() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                page = 1;
                referList(page, 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                page++;
                referList(page, 10);
            }
        });
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void loadData() {
        if (refreshLayout != null)
            refreshLayout.autoRefresh();
    }

    private void referList(final int page, int pageSize) {
        addObserver(shopApi.shopList(page, 10, ""), new NetworkObserver<ApiResult<ShopGoods>>() {

            @Override
            public void onReady(ApiResult<ShopGoods> shopGoodsApiResult) {
                if (shopGoodsApiResult.getData() != null &&
                        shopGoodsApiResult.getData().getData() != null
                        && shopGoodsApiResult.getData().getData().size() > 0) {
                    if (page == 1)
                        shopGoods.clear();
                    shopGoods.addAll(shopGoodsApiResult.getData().getData());
                    mallAdapter.notifyDataSetChanged();
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
