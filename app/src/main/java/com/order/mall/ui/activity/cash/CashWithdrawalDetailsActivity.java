package com.order.mall.ui.activity.cash;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.CashScoreDetails;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CashWithdrawalDetailsActivity extends BaseActivity {

    Unbinder unbinder;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.tv_status)
    TextView statusTv;
    @BindView(R.id.tv_price)
    TextView price;
    @BindView(R.id.tv_amount)
    TextView amount;
    @BindView(R.id.tv_beizhu)
    TextView remark;

    private IUserApi iUserApi;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        String id = getIntent().getStringExtra("id");
        init();
        getDetails(id);
    }

    private void getDetails(String id) {
        addObserver(iUserApi.oneCashBalanceDetails(id), new NetworkObserver<ApiResult<CashScoreDetails>>() {

            @Override
            public void onReady(ApiResult<CashScoreDetails> cashScoreDetailsApiResult) {
                if (cashScoreDetailsApiResult.getData() != null) {
                    initData(cashScoreDetailsApiResult.getData());
                }
            }
        });

    }

    private void initData(CashScoreDetails data) {
        orderId.setText(data.getId());
        time.setText(data.getCreateDate());
        price.setText(data.getCashValue() / 100 + "现金积分");
//        amount.setText(data.getTradeAccount());
        remark.setText(data.getRemark());

        int cashType = data.getCashType();

        if (cashType == 0) {
            statusTv.setText("发起提现");
        } else if (cashType == 1) {
            statusTv.setText("发起转账");
        } else if (cashType == 2) {
            statusTv.setText("转账审核通过");
        } else if (cashType == 3) {
            statusTv.setText("转账审核失败");
        } else if (cashType == 4) {
            statusTv.setText("提现审核通过");
        } else if (cashType == 5) {
            statusTv.setText("提现审核失败");
        } else if (cashType == 6) {
            statusTv.setText("奖金积分-转入");
        } else if (cashType == 7) {
            statusTv.setText("现金积分收益-转入");


        }
    }

    private void init() {
        line1.setVisibility(View.GONE);
        line5.setVisibility(View.VISIBLE);
        tvTitle.setText("详细信息");
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
