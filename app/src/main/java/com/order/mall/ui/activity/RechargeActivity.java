package com.order.mall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.RechargeSuccess;
import com.order.mall.model.A;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 充值
 */
public class RechargeActivity extends BaseActivity implements TextWatcher, RadioGroup.OnCheckedChangeListener {

    Unbinder unbinder;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_equal_grade)
    TextView tvEqualGrade;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.line_zhifubao)
    View lineZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.line_weixin)
    View lineWeixin;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.radio_zhifubao)
    RadioButton radioZhifubao;
    @BindView(R.id.radio_weixin)
    RadioButton radioWeixin;
    @BindView(R.id.radio_bank)
    RadioButton radioBank;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private IUserApi iUserApi;
    private double rate;
    private int rechargeNum = 0;
    private int payWay = 2;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        unbinder = ButterKnife.bind(this);
        etPrice.addTextChangedListener(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        radioGroup.check(R.id.radio_zhifubao);
        radioGroup.setOnCheckedChangeListener(this);
        userId = SharedPreferencesHelp.getInstance(this).getUser().getId();
        getExchangeRate();
        init();
    }

    private void getExchangeRate() {
        addObserver(iUserApi.getExchangeRate(), new NetworkObserver() {
            @Override
            public void onReady(ApiResult apiResult) {
                if (apiResult.getData() != null) {
                    rate = (double) apiResult.getData();
                }
            }

        });

    }

    @OnClick(R.id.pay)
    public void pay() {
        if (rechargeNum != 0) {
            addObserver(iUserApi.topUp(userId, rechargeNum, payWay), new NetworkObserver<ApiResult<RechargeSuccess>>() {
                @Override
                public void onReady(ApiResult<RechargeSuccess> rechargeSuccessApiResult) {
                    if (rechargeSuccessApiResult != null && rechargeSuccessApiResult.getData() != null) {
                        Intent intent = new Intent(RechargeActivity.this, ReportOrderSubActivity.class);
                        intent.putExtra("id", rechargeSuccessApiResult.getData().getId());
                        startActivity(intent);
                    }

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }

                @Override
                public void onNext(ApiResult<RechargeSuccess> rechargeSuccessApiResult) {
                    super.onNext(rechargeSuccessApiResult);
                }

                @Override
                public void onCompleted() {
                    super.onCompleted();
                }
            });
        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    private void init() {
        tvTitle.setText("充值");
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString();
        if (!string.isEmpty()) {
            rechargeNum = (int) (Integer.valueOf(string) * rate);
            tvEqualGrade.setText("折合" + rechargeNum + "报单积分");
        } else {
            tvEqualGrade.setText("折合0报单积分");

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_zhifubao:
                payWay = 2;
                break;
            case R.id.radio_weixin:
                payWay = 1;
                break;
            case R.id.radio_bank:
                payWay = 3;
                break;
        }
    }
}
