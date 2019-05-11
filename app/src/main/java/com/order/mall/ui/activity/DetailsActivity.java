package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.financial.FinancialProduct;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.pay.QiangdanPayActivity;
import com.order.mall.ui.widget.ProgressView;
import com.order.mall.util.GlideImageLoader;
import com.order.mall.util.TimeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.order.mall.ui.fragment.trade.QiangdanFragment.INTENT_KEY_PRODUCT;

public class DetailsActivity extends BaseActivity {

    public  static final String INTENT_KEY_PRODUCT_ID = "INTNET_KEY_PRODUCT_ID";
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
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_qiangdan)
    TextView tvQiangdan;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.mairu)
    TextView mairu;
    @BindView(R.id.daoqi)
    TextView daoqi;
    @BindView(R.id.progress_view)
    ProgressView progressView;
    @BindView(R.id.tv_today)
    TextView tvToday;
    @BindView(R.id.tv_end_day)
    TextView tvEndDay;

    private FinancialProduct.DataBean data;
    private List<String> urls;
    private String title;
    private float priceTxt;
    private String timeTxt;
    private String dayProfit;
    private float xianjin;
    private int endDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setUp();
        init();
    }

    @Override
    protected void setUp() {
        super.setUp();
        if (getIntent() != null) {
            data = getIntent().getParcelableExtra(INTENT_KEY_PRODUCT);
            urls = data.getImagess();
            title = data.getTitle();
            priceTxt = data.getTradeScore();
            timeTxt = TimeUtils.timeConvertText(data.getEndTimeMs());
            xianjin = data.getPredictInterest();
            dayProfit = data.getAnnualizedRate() + "%";
            endDay = data.getDays();
        }
    }

    private void init() {
        SpannableStringBuilder style = new SpannableStringBuilder(xianjin + "现金积分");
        style.setSpan(new AbsoluteSizeSpan(20, true), 0, style.length() - 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(10, true), style.length() - 4, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvJifen.setText(style);
        SpannableStringBuilder style1 = new SpannableStringBuilder(endDay + "天");
        style1.setSpan(new AbsoluteSizeSpan(20, true), 0, style1.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style1.setSpan(new AbsoluteSizeSpan(10, true), style1.length() - 1, style1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDay.setText(style1);
        initBanner();
        tvTitle.setText(title);
        tvPrice.setText(priceTxt + "");
        time.setText(timeTxt);
        tvPreceter.setText(dayProfit);
        tvTips.setText(getString(R.string.days, endDay));
        tvToday.setText("今日(" + TimeUtils.getDate(System.currentTimeMillis()) +")");
        if (data != null)
        tvEndDay.setText(TimeUtils.getDate(System.currentTimeMillis() + data.getEndTimeMs()));
    }

    private void initBanner() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
        if (urls != null && urls.size() > 0) {
            banner.setImages(urls);
            banner.start();
        }
    }

    @OnClick(R.id.tv_qiangdan)
    public void qiangdan() {
        Intent intent = new Intent(this, QiangdanPayActivity.class);
        if (data != null)
        intent.putExtra(INTENT_KEY_PRODUCT_ID , data.getId());
        startActivity(intent);
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    private void net(){

    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }
}
