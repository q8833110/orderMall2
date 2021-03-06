package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IFinancialProductsApi;
import com.order.mall.data.network.financial.SellOrder;
import com.order.mall.data.network.login.UserRespDTO;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.pay.QiangdanPayActivity;
import com.order.mall.ui.activity.user.TradeAllActivity;
import com.order.mall.util.GlideImageLoader;
import com.order.mall.util.ImageStringUtils;
import com.order.mall.util.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.order.mall.ui.activity.DetailsMallActivity.INTNET_KEY_PAY;
import static com.order.mall.ui.fragment.trade.SellFragment.INTENT_KEY_ORDRE_ID;

public class DetailsSellActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_qiangdan_title)
    TextView tvQiangdanTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.jifen_title)
    TextView jifenTitle;
    @BindView(R.id.jifen)
    TextView jifen;
    @BindView(R.id.baodanjifen)
    TextView baodanjifen;
    @BindView(R.id.et_jifen)
    TextView etJifen;
    @BindView(R.id.tv_qiangdan)
    TextView tvQiangdan;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String id;

    private int userId ;
    private IFinancialProductsApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_details);
        ButterKnife.bind(this);
        setUp();
        init();
    }

    @Override
    protected void setUp() {
        super.setUp();
        if (getIntent() != null) {
            id = getIntent().getStringExtra(INTENT_KEY_ORDRE_ID);
        }
    }

    private void initBanner() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
    }

    private void init() {
        api = RetrofitUtils.getInstance().getRetrofit().create(IFinancialProductsApi.class);
        UserRespDTO user = SharedPreferencesHelp.getInstance(this).getUser() ;
        if (user != null)
        userId = (int) user.getId();
        initBanner();
        addNet();
    }

    @OnClick(R.id.tv_qiangdan)
    public void qiangdan() {
        Intent intent = new Intent(this, QiangdanPayActivity.class);
        startActivityForResult(intent ,INTNET_KEY_PAY);
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

    public void addNet() {
        if (!TextUtils.isEmpty(id))
            addObserver(api.sellOrderDetail(id), new NetworkObserver<ApiResult<SellOrder.DataBean>>() {

                @Override
                public void onReady(ApiResult<SellOrder.DataBean> sellOrderApiResult) {
                    if (sellOrderApiResult.getData() != null) {
                        addData(sellOrderApiResult.getData());
                    } else {
                        showToast(sellOrderApiResult.getMessage());
                    }
                }
            });
    }

    private void addData(SellOrder.DataBean dataBean) {
        if (dataBean.getImagess() != null && dataBean.getImagess().size() > 0)
        banner.setImages(dataBean.getImagess());
        else{
            if (!TextUtils.isEmpty(dataBean.getImages())){
                List<String> urls = ImageStringUtils.getImages(dataBean.getImages());
                if (urls != null && urls.size() > 0)
                banner.setImages(urls);
            }
        }
        banner.start();
        tvTitle.setText(dataBean.getTitle());
        tvPrice.setText(dataBean.getTradeScore() +"");
        jifen.setText(dataBean.getInterest() +"");
        etJifen.setText(dataBean.getTradeScore() + dataBean.getInterest() +"");
    }

    @OnClick(R.id.tv_sell)
    void sell(){
        if (userId != 0)
        addObserver(api.sell(id) , new NetworkObserver<ApiResult<Boolean>>(){

            @Override
            public void onReady(ApiResult<Boolean> booleanApiResult) {
                if (booleanApiResult.getData() != null && booleanApiResult.getData()){
                    showToast(booleanApiResult.getMessage());
                    Intent intent = new Intent(DetailsSellActivity.this, TradeAllActivity.class);
                    intent.putExtra("position", 4);
                    startActivity(intent);
                }else{
                    showToast(booleanApiResult.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (resultCode == INTNET_KEY_PAY){
                this.finish();
            }
        }
    }
}
