package com.order.mall.ui.activity.cash;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.CashSuccess;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提现详情
 */
public class WithdrawalStatusActivity extends BaseActivity {

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
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.id)
    TextView orderId;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.service_change)
    TextView serviceChange;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.pay_style)
    TextView payStyle;
    @BindView(R.id.iv_pay_style)
    ImageView ivPayStyle;
    @BindView(R.id.amount)
    TextView amount;
    private String id;
    private IUserApi iUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_status);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        id = getIntent().getStringExtra("id");
        tvTitle.setText("详细信息");
        getDetails();
    }

    private void getDetails() {
        addObserver(iUserApi.oneUserEncashment(id), new NetworkObserver<ApiResult<CashSuccess>>() {
            @Override
            public void onReady(ApiResult<CashSuccess> apiResult) {
                if (apiResult.getData() != null) {
                    init(apiResult.getData());
                }
            }

        });

    }

    private void init(CashSuccess data) {
        tvInfo.setText(data.getEncashValue()/100 + "现金积分=" + data.getRmbValue()/100 + "元");
        orderId.setText(data.getId());
        time.setText(data.getCreateDate());
        price.setText(data.getEncashValue() / 100 + "");
        serviceChange.setText(data.getServiceValue()/100);
        if (data.getEncashStatus() == 0) {
            tvStatus.setText("已提交提现订单，待节点处理");
            tvProgress.setText("请耐心等待后台节点处理");

        } else if (data.getEncashStatus() == 1) {
            tvStatus.setText("提现成功");
            tvProgress.setText("节点已处理，提现金额已到指定账户");
        } else if (data.getEncashStatus() == 2) {
            tvStatus.setText("提现失败");
            tvProgress.setText("现金积分冻结中目前无法进行提现");

        }
    }


    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
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
