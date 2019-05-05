package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.pay.QiangdanPayActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.tv_preceter)
    TextView tvPreceter;
    @BindView(R.id.tv_precnter_title)
    TextView tvPrecnterTitle;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_day)
    TextView tvDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        SpannableStringBuilder style = new SpannableStringBuilder("800天");
        style.setSpan(new AbsoluteSizeSpan(20 , true), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(10 , true), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvJifen.setText(style);
        tvDay.setText(style);
    }

    @OnClick(R.id.tv_qiangdan)
    public void qiangdan(){
        Intent intent = new Intent(this , QiangdanPayActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @OnClick(R.id.back)
    public void back(){
        this.finish();
    }
}
