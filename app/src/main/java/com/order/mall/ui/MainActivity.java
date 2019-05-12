package com.order.mall.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.fragment.main.MainFragment;
import com.order.mall.ui.fragment.main.MallFragment;
import com.order.mall.ui.fragment.main.TradeFragment;
import com.order.mall.ui.fragment.main.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements MainFragment.Listener {

    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.tv_main)
    TextView tvMain;
    @BindView(R.id.iv_trade)
    ImageView ivTrade;
    @BindView(R.id.tv_trade)
    TextView tvTrade;
    @BindView(R.id.ll_trade)
    LinearLayout llTrade;
    @BindView(R.id.iv_mall)
    ImageView ivMall;
    @BindView(R.id.tv_mall)
    TextView tvMall;
    @BindView(R.id.ll_mall)
    LinearLayout llMall;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.ll_main)
    LinearLayout llMain;

    Unbinder unbinder;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        init();
        int position = getIntent().getIntExtra("position", 0);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            hideAll(transaction);
            if (position == 2) {
                switchFragment(transaction, 2);
            } else if (position == 3) {
                switchFragment(transaction, 3);
            } else {
                switchFragment(transaction, 1);
            }

        }
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_main, R.id.ll_mall, R.id.ll_trade, R.id.ll_user})
    public void clickFragment(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_main:
                hideAll(transaction);
                switchFragment(transaction, 1);
                break;
            case R.id.ll_mall:
                hideAll(transaction);
                switchFragment(transaction, 3);
                break;
            case R.id.ll_trade:
                hideAll(transaction);
                switchFragment(transaction, 2);
                break;
            case R.id.ll_user:
                hideAll(transaction);
                switchFragment(transaction, 4);
                break;
            default:
                hideAll(transaction);
                switchFragment(transaction, 1);
                break;
        }
    }

    private void hideAll(FragmentTransaction transaction) {
        MainFragment fragment = (MainFragment) fragmentManager.findFragmentByTag(MainFragment.class.getSimpleName());
        if (fragment != null) {
            transaction.hide(fragment);
        }
        MallFragment mallFragment = (MallFragment) fragmentManager.findFragmentByTag(MallFragment.class.getSimpleName());
        if (mallFragment != null) {
            transaction.hide(mallFragment);
        }

        TradeFragment tradeFragment = (TradeFragment) fragmentManager.findFragmentByTag(TradeFragment.class.getSimpleName());
        if (tradeFragment != null) {
            transaction.hide(tradeFragment);
        }
        UserFragment userFragment = (UserFragment) fragmentManager.findFragmentByTag(UserFragment.class.getSimpleName());
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    private void switchFragment(FragmentTransaction transaction, int index) {
        switch (index) {
            case 1:
                MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag(MainFragment.class.getSimpleName());
                if (mainFragment != null) {
                    if (mainFragment.getListener() == null)
                        mainFragment.setListener(this);
                    transaction.show(mainFragment);
                } else {
                    mainFragment = MainFragment.newInstance();
                    mainFragment.setListener(this);
                    transaction.add(R.id.fragment, mainFragment, MainFragment.class.getSimpleName());
                }
                break;
            case 3:
                MallFragment mallFragment = (MallFragment) fragmentManager.findFragmentByTag(MallFragment.class.getSimpleName());
                if (mallFragment != null) {
                    transaction.show(mallFragment);
                } else {
                    mallFragment = MallFragment.newInstance();
                    transaction.add(R.id.fragment, mallFragment, MallFragment.class.getSimpleName());
                }
                break;
            case 2:
                TradeFragment tradeFragment = (TradeFragment) fragmentManager.findFragmentByTag(TradeFragment.class.getSimpleName());
                if (tradeFragment != null) {
                    transaction.show(tradeFragment);
                } else {
                    tradeFragment = TradeFragment.newInstance();
                    transaction.add(R.id.fragment, tradeFragment, TradeFragment.class.getSimpleName());
                }
                break;
            case 4:
                UserFragment userFragment = (UserFragment) fragmentManager.findFragmentByTag(UserFragment.class.getSimpleName());
                if (userFragment != null) {
                    transaction.show(userFragment);
                } else {
                    userFragment = UserFragment.newInstance();
                    transaction.add(R.id.fragment, userFragment, UserFragment.class.getSimpleName());
                }
                break;
            default:
                MainFragment fragment = (MainFragment) fragmentManager.findFragmentByTag(MainFragment.class.getSimpleName());
                if (fragment != null) {
                    transaction.show(fragment);
                } else {
                    fragment = MainFragment.newInstance();
                    transaction.add(R.id.fragment, fragment, MainFragment.class.getSimpleName());
                }
                break;
        }
        transaction.commit();
        select(index);
    }

    private void select(int index) {
        switch (index) {
            case 1:
                ivMain.setBackgroundResource(R.drawable.main_select);
                tvMain.setTextColor(getResources().getColor(R.color.colorFullRed));
                ivMall.setBackgroundResource(R.drawable.mall_unselect);
                tvMall.setTextColor(getResources().getColor(R.color.colorGray666));
                ivTrade.setBackgroundResource(R.drawable.trade_unselect);
                tvTrade.setTextColor(getResources().getColor(R.color.colorGray666));
                ivUser.setBackgroundResource(R.drawable.user_unselect);
                tvUser.setTextColor(getResources().getColor(R.color.colorGray666));
                break;
            case 2:
                ivMain.setBackgroundResource(R.drawable.main_unselect);
                tvMain.setTextColor(getResources().getColor(R.color.colorGray666));
                ivMall.setBackgroundResource(R.drawable.mall_unselect);
                tvMall.setTextColor(getResources().getColor(R.color.colorGray666));
                ivTrade.setBackgroundResource(R.drawable.trade_select);
                tvTrade.setTextColor(getResources().getColor(R.color.colorFullRed));
                ivUser.setBackgroundResource(R.drawable.user_unselect);
                tvUser.setTextColor(getResources().getColor(R.color.colorGray666));
                break;
            case 3:
                ivMain.setBackgroundResource(R.drawable.main_unselect);
                tvMain.setTextColor(getResources().getColor(R.color.colorGray666));
                ivMall.setBackgroundResource(R.drawable.mall_select);
                tvMall.setTextColor(getResources().getColor(R.color.colorFullRed));
                ivTrade.setBackgroundResource(R.drawable.trade_unselect);
                tvTrade.setTextColor(getResources().getColor(R.color.colorGray666));
                ivUser.setBackgroundResource(R.drawable.user_unselect);
                tvUser.setTextColor(getResources().getColor(R.color.colorGray666));
                break;
            case 4:
                ivMain.setBackgroundResource(R.drawable.main_unselect);
                tvMain.setTextColor(getResources().getColor(R.color.colorGray666));
                ivMall.setBackgroundResource(R.drawable.mall_unselect);
                tvMall.setTextColor(getResources().getColor(R.color.colorGray666));
                ivTrade.setBackgroundResource(R.drawable.trade_unselect);
                tvTrade.setTextColor(getResources().getColor(R.color.colorGray666));
                ivUser.setBackgroundResource(R.drawable.user_select);
                tvUser.setTextColor(getResources().getColor(R.color.colorFullRed));
                break;
            default:
                break;
        }
    }

    @Override
    public void toQiangdan() {
        FragmentTransaction transaction;
        if (fragmentManager != null) {
            transaction = fragmentManager.beginTransaction();
            hideAll(transaction);
            switchFragment(transaction, 2);
        }
    }
}
