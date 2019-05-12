package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IShopApi;
import com.order.mall.data.network.shop.ShopGoods;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.pay.QiangdanPayActivity;
import com.order.mall.util.GlideImageLoader;
import com.order.mall.util.ImageStringUtils;
import com.order.mall.util.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.order.mall.ui.fragment.main.MallFragment.INTENT_KEY_SHOP_ID;

public class DetailsMallActivity extends BaseActivity {
    public static final String INTENT_KEY_SHOP_ID  = "INTENT_KEY_SHOP_ID";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.vt_price)
    TextView vtPrice;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.freight)
    TextView freight;
    @BindView(R.id.rl_more)
    RelativeLayout rlMore;
    @BindView(R.id.tv_mall)
    TextView tvMall;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wb)
    WebView wb;
    private int shopId;
    private IShopApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);
        ButterKnife.bind(this);
        setUp();
        init();
        addNet();
        initBanner();
    }

    @Override
    protected void setUp() {
        super.setUp();
        if (getIntent() != null)
            shopId = getIntent().getIntExtra(INTENT_KEY_SHOP_ID, -1);
    }

    private void initBanner() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
    }

    private void init() {
        api = RetrofitUtils.getInstance().getRetrofit().create(IShopApi.class);
        WebSettings settings = wb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    private ShopGoods.ShopGood shopGood ;
    private void addNet() {
        addObserver(api.shopDetails(shopId), new NetworkObserver<ApiResult<ShopGoods.ShopGood>>() {

            @Override
            public void onReady(ApiResult<ShopGoods.ShopGood> shopGoodApiResult) {
                if (shopGoodApiResult.getData() != null) {
                    shopGood = shopGoodApiResult.getData();
                    List<String> pictures = ImageStringUtils.getImages(shopGood.getPicture());
                    if (pictures != null && pictures.size() > 0) {
                        banner.setImages(pictures);
                        banner.start();
                    }
                    tvTitle.setText(shopGood.getName());
                    tvTitle2.setText(shopGood.getSynopsis());
                    vtPrice.setText(shopGood.getPrice() + "");
                    if (shopGood.getFreight() == 0) {
                        freight.setText("免运费");
                    } else {
                        freight.setText(shopGood.getFreight() + "");
                    }
                    wb.loadData(getHtmlData(shopGood.getDetails()), "text/html;charset=utf-8","utf-8");

                } else {
                    showToast(shopGoodApiResult.getMessage());
                }
            }
        });
    }


    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    public static int INTNET_KEY_PAY =  0x111;
    @OnClick(R.id.tv_mall)
    public void mall(){
        Intent intent = new Intent(this , QiangdanPayActivity.class);
        if (shopGood != null)
        intent.putExtra(INTENT_KEY_SHOP_ID ,shopGood.getId());
        startActivityForResult(intent , INTNET_KEY_PAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == INTNET_KEY_PAY) {
                this.finish();
            }
        }
    }
}
