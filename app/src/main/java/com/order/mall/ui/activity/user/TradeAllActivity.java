package com.order.mall.ui.activity.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.kcrason.dynamicpagerindicatorlibrary.DynamicPagerIndicator;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.TextAdapter;
import com.order.mall.ui.fragment.user.AllShoppingFragment;
import com.order.mall.ui.fragment.user.AllTradeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TradeAllActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.vg)
    ViewPager pager;
    @BindView(R.id.dynamic_pager_indicator1)
    DynamicPagerIndicator dynamicPagerIndicator1;

    Unbinder unbinder;
    private String[] titles = new String[]{
            "全部", "已抢购", "已收益", "已挂卖", "已卖出"
    };
    private int position;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_all);
        unbinder = ButterKnife.bind(this);
        position = getIntent().getIntExtra("position", 0);
        init();
    }

    private void init() {
        // 设置Tab底部选中的指示器 Indicator的颜色
        List<Fragment> list = new ArrayList<>();
        list.add(AllTradeFragment.newInstance(1));
        list.add(AllTradeFragment.newInstance(2));
        list.add(AllTradeFragment.newInstance(3));
        list.add(AllTradeFragment.newInstance(4));
        list.add(AllTradeFragment.newInstance(5));
        pager.setAdapter(new TextAdapter(getSupportFragmentManager(), titles, list));
        dynamicPagerIndicator1.setViewPager(pager);
        pager.setCurrentItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }
}
