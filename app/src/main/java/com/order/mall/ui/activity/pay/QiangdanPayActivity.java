package com.order.mall.ui.activity.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IFinancialProductsApi;
import com.order.mall.data.network.IShopApi;
import com.order.mall.data.network.financial.FinancialProductOrder;
import com.order.mall.data.network.financial.PreyPay;
import com.order.mall.data.network.shop.ShopOrder;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.user.AddressActivity;
import com.order.mall.ui.activity.user.TradeAllActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.order.mall.ui.activity.DetailsActivity.INTENT_KEY_PRODUCT_ID;
import static com.order.mall.ui.activity.DetailsMallActivity.INTENT_KEY_SHOP_ID;

public class QiangdanPayActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_baodan)
    TextView tvBaodan;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_baodna1)
    TextView tvBaodna1;
    @BindView(R.id.tv_jifen1)
    TextView tvJifen1;
    @BindView(R.id.tv_pay)
    TextView tvPay;

    IFinancialProductsApi api;
    IShopApi shopApi;
    @BindView(R.id.tv_receive)
    TextView tvReceive;
    @BindView(R.id.rl_receive)
    RelativeLayout rlReceive;

    private int productsId;

    private int userId;

    private int shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiangdan);
        ButterKnife.bind(this);
        setUp();
        init();
        net();
    }

    @Override
    protected void setUp() {
        super.setUp();
        if (getIntent() != null) {
            productsId = getIntent().getIntExtra(INTENT_KEY_PRODUCT_ID, -1);
            shopId = getIntent().getIntExtra(INTENT_KEY_SHOP_ID, -1);
        }
    }

    private void showBaodan(boolean baodan) {
        if (baodan) {
            tvBaodan.setText("报单积分");
            tvBaodna1.setText("报单积分");
            rlReceive.setVisibility(View.GONE);
        } else {
            tvBaodan.setText("消费积分");
            tvBaodna1.setText("消费积分");
            rlReceive.setVerticalGravity(View.VISIBLE);
        }
    }

    private void init() {
        if (productsId != -1)
            showBaodan(true);
        else {
            showBaodan(false);
        }
        shopApi = RetrofitUtils.getInstance().getRetrofit().create(IShopApi.class);
        api = RetrofitUtils.getInstance().getRetrofit().create(IFinancialProductsApi.class);
        userId = (int) SharedPreferencesHelp.getInstance(this).getUser().getId();

    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    private void net() {
        if (productsId != -1) {
            addObserver(api.prepay(productsId, userId), new NetworkObserver<ApiResult<PreyPay>>() {

                @Override
                public void onReady(ApiResult<PreyPay> preyPayApiResult) {
                    if (preyPayApiResult.getData() != null) {
                        PreyPay preyPay = preyPayApiResult.getData();
                        tvJifen.setText(preyPay.getTradeScore() + "");
                        tvJifen1.setText(preyPay.getTradeBalance() + "");
                    }
                }
            });
        } else if (shopId != -1) {
            addObserver(shopApi.shopPay(shopId, userId), new NetworkObserver<ApiResult<PreyPay>>() {

                @Override
                public void onReady(ApiResult<PreyPay> preyPayApiResult) {
                    if (preyPayApiResult.getData() != null) {
                        PreyPay preyPay = preyPayApiResult.getData();
                        tvJifen.setText(preyPay.getTradeScore() + "");
                        tvJifen1.setText(preyPay.getTradeBalance() + "");
                    }
                }
            });
        }
    }

    @OnClick(R.id.tv_pay)
    public void pay() {
        if (productsId != -1) {
            addObserver(api.pay(productsId, userId), new NetworkObserver<ApiResult<FinancialProductOrder>>() {

                @Override
                public void onReady(ApiResult<FinancialProductOrder> financialProductOrderApiResult) {
                    if (financialProductOrderApiResult.getData() != null) {
                        showToast("抢单成功");
                        Intent intent = new Intent(QiangdanPayActivity.this, TradeAllActivity.class);
                        intent.putExtra("position", 1);
                        startActivity(intent);
                    } else {
                        showToast(financialProductOrderApiResult.getMessage());
                    }
                }
            });
        } else if (shopId != -1) {
            addObserver(shopApi.pay(shopId, userId), new NetworkObserver<ApiResult<ShopOrder>>() {

                @Override
                public void onReady(ApiResult<ShopOrder> financialProductOrderApiResult) {
                    if (financialProductOrderApiResult.getData() != null) {
                        showToast("购物成功");
                        // TODO: 2019/5/11/011  跳转商品已购列表
                        Intent intent = new Intent(QiangdanPayActivity.this, TradeAllActivity.class);
                        intent.putExtra("position", 1);
                        startActivity(intent);
                    } else {
                        showToast(financialProductOrderApiResult.getMessage());
                    }
                }
            });
        }
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    public static final int REQUEST_CODE_RECEIVE = 01 ;
    public static final String RESULT_ADDRESS  = "RESULT_ADDRESS" ;
    @OnClick(R.id.rl_receive)
    public void choseReceive(){
        Intent intent = new Intent(this , AddressActivity.class);
        startActivityForResult(intent , REQUEST_CODE_RECEIVE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RECEIVE){
            if (data != null) {
                String address = data.getStringExtra(RESULT_ADDRESS);
                tvReceive.setText(address);
            }
        }
    }
}
