package com.order.mall.ui.activity.consume;

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
import com.order.mall.ui.fragment.user.AllRechargeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ConsumeJifenDetailsActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.vg)
    ViewPager pager ;
    @BindView(R.id.dynamic_pager_indicator1)
    DynamicPagerIndicator dynamicPagerIndicator1;

    Unbinder unbinder ;
    private String[] titles = new String[]{
            "全部" ,"购物", "转入" , "转出"
    };
    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifenmingxi);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        // 设置Tab底部选中的指示器 Indicator的颜色
        List<Fragment> list = new ArrayList<>();
        list.add(AllRechargeFragment.newInstance(1));
        list.add(AllRechargeFragment.newInstance(2));
        list.add(AllRechargeFragment.newInstance(3));
        list.add(AllRechargeFragment.newInstance(4));
        pager.setAdapter(new TextAdapter(getSupportFragmentManager(), titles, list));
        dynamicPagerIndicator1.setViewPager(pager);
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
