package com.order.mall.ui.activity;

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
 * 报单积分
 */
public class ReportDetailActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
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
    @BindView(R.id.amount)
    TextView amount;
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
    @BindView(R.id.circle1)
    ImageView circle1;
    @BindView(R.id.circle2)
    ImageView circle2;
    @BindView(R.id.mid_line)
    View midLine;


    Unbinder unbinder;
    private IUserApi iUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        unbinder = ButterKnife.bind(this);

        tvTitle.setText("详细信息");

        String id = getIntent().getStringExtra("id");
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        circle1.setSelected(true);
        getDetails(id);

    }

    private void getDetails(String id) {
        addObserver(iUserApi.rechargeDetails(id), new NetworkObserver<ApiResult<RechargeDetails>>() {


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
        tvInfo.setText("+" + data.getTradeScore() + "报单积分");
        id.setText(data.getId());
        price.setText(data.getAmount() + "");
        time.setText(data.getCreateTime());
        payStyle.setText(data.getPayWayName());
        paymentType.setText(data.getPayWayName());
        amount.setText(data.getToAccount());
        name.setText(data.getToUser());
        Glide.with(this).load(data.getToQrCode()).into(ivQrcode);

        String proofOfPay = data.getProofOfPay();
        if (proofOfPay.contains(";")) {
            String[] split = proofOfPay.split(";");
            Glide.with(this).load(split[0]).into(pay1);
            Glide.with(this).load(split[1]).into(pay2);

        } else {
            Glide.with(this).load(proofOfPay).into(pay1);
        }
        int status = data.getStatus();
        switch (status) {
            case -2:
                Glide.with(this).load(R.drawable.pay_failure).into(ivStatus);
                tvStatus.setText("充值失败");
                tvProgress.setText("上传凭证无效，系统未查询到到账记录");
                circle2.setSelected(false);
                midLine.setBackgroundColor(getResources().getColor(R.color.colorFullRed));
                break;
            case -1:
                tvStatus.setText("订单已取消");
                Glide.with(this).load(R.drawable.pay_failure).into(ivStatus);
                circle2.setSelected(false);
                midLine.setBackgroundColor(getResources().getColor(R.color.colorFullRed));
                break;
            case 0:
                tvStatus.setText("待支付");
                Glide.with(this).load(R.drawable.success).into(ivStatus);
                circle2.setSelected(false);
                midLine.setBackgroundColor(getResources().getColor(R.color.colorBDBDBD));
                break;
            case 1:
                Glide.with(this).load(R.drawable.success).into(ivStatus);
                tvStatus.setText("已提交订单，等待节点处理");
                tvProgress.setText("请耐心等待后台节点处理");
                circle2.setSelected(false);
                midLine.setBackgroundColor(getResources().getColor(R.color.colorBDBDBD));
                break;
            case 2:
                Glide.with(this).load(R.drawable.success).into(ivStatus);
                tvStatus.setText("订单已完成");
                tvProgress.setText("节点已确认，充值积分已到账");
                circle2.setSelected(true);
                midLine.setBackgroundColor(getResources().getColor(R.color.colorFullRed));

                break;
        }
        if (data.getPayWayValue() == 1) {
            //微信
            Glide.with(this).load(R.drawable.weixin).into(ivPayStyle);
        } else if (data.getPayWayValue() == 2) {
            //支付宝
            Glide.with(this).load(R.drawable.zhifubao).into(ivPayStyle);

        } else if (data.getPayWayValue() == 3) {
            //银行卡
            Glide.with(this).load(R.drawable.bank).into(ivPayStyle);

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
