package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.RechargeDetails;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 订单提交
 */
public class ReportOrderSubActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.pay_style)
    TextView payStyle;
    @BindView(R.id.iv_pay_style)
    ImageView ivPayStyle;
    @BindView(R.id.payment_type)
    TextView paymentType;
    @BindView(R.id.iv_amount)
    ImageView ivAmount;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.payment_qrcode)
    TextView paymentQrcode;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.pay1)
    ImageView pay1;
    @BindView(R.id.pay2)
    ImageView pay2;

    Unbinder unbinder;
    private IUserApi iUserApi;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_order_submit);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        orderId = getIntent().getStringExtra("id");
        tvTitle.setText("订单详情");
        getDetails();
    }

    private void getDetails() {
        addObserver(iUserApi.rechargeDetails(orderId), new NetworkObserver<ApiResult<RechargeDetails>>() {


            @Override
            public void onReady(ApiResult<RechargeDetails> rechargeDetailsApiResult) {
                if (rechargeDetailsApiResult.getData() != null) {
                    init(rechargeDetailsApiResult.getData());

                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    private void init(RechargeDetails data) {
        id.setText(data.getId());
        price.setText(data.getAmount() + "");
        time.setText(data.getCreateTime());
        payStyle.setText(data.getPayWayName());
        paymentType.setText(data.getPayWayName());
        amount.setText(data.getToAccount());
        name.setText(data.getToUser());
        Glide.with(this).load(data.getToQrCode()).into(ivQrcode);

        String proofOfPay = data.getProofOfPay();
//        if (proofOfPay.contains(";")) {
//            String[] split = proofOfPay.split(";");
//            Glide.with(this).load(split[0]).into(pay1);
//            Glide.with(this).load(split[1]).into(pay2);
//
//        } else {
//            Glide.with(this).load(proofOfPay).into(pay1);
//        }
//        if (data.getPayWayValue() == 1) {
//            //微信
//            Glide.with(this).load(R.drawable.weixin).into(ivPayStyle);
//        } else if (data.getPayWayValue() == 2) {
//            //支付宝
//            Glide.with(this).load(R.drawable.zhifubao).into(ivPayStyle);
//
//        } else if (data.getPayWayValue() == 3) {
//            //银行卡
//            Glide.with(this).load(R.drawable.bank).into(ivPayStyle);
//
//        }
    }

    @OnClick(R.id.tv_cancel)
    public void cancel() {
        //取消订单
        addObserver(iUserApi.topUpCancle(orderId), new NetworkObserver() {
            @Override
            public void onReady(ApiResult apiResult) {
                if (apiResult.getData() != null) {
                    boolean data = (boolean) apiResult.getData();
                    if (data) {
                        //成功
                        showToast("取消充值成功");
                        finish();
                    }
                }
            }
        });

    }

    @OnClick(R.id.tv_already_pay)
    public void pay() {
        Intent intent = new Intent(this, RechargeActivity.class);
        startActivity(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
