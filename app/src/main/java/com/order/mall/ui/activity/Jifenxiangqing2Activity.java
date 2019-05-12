package com.order.mall.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.BonusScoreDetails;
import com.order.mall.data.network.user.ConsumeDetails;
import com.order.mall.data.network.user.TradeDetails;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jifenxiangqing2Activity extends BaseActivity {

    @BindView(R.id.order_id)
    TextView mOrderIdTv;
    @BindView(R.id.time)
    TextView mTimeTv;
    @BindView(R.id.tv_price)
    TextView mPriceTv;
    @BindView(R.id.tv_beizhu)
    TextView mRemarkTv;
    @BindView(R.id.caozuo)
    TextView caozuo;
    @BindView(R.id.status)
    TextView status;

    private IUserApi iUserApi;
    private int type;   //1  奖金积分明细   2 消费积分明细

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_jifenxiangqing2);
        ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);

        String id = getIntent().getStringExtra("id");
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {

            oneBonusBalanceDetails(id);
        } else if (type == 2) {
            oneConsumeBalanceDetails(id);
        }

    }

    private void oneBonusBalanceDetails(String id) {
        addObserver(iUserApi.oneBonusBalanceDetails(id), new NetworkObserver<ApiResult<BonusScoreDetails>>() {
            @Override
            public void onReady(ApiResult<BonusScoreDetails> bonusScoreDetailsApiResult) {
                initBonusData(bonusScoreDetailsApiResult.getData());
            }
        });
    }

    private void oneConsumeBalanceDetails(String id) {
        addObserver(iUserApi.oneConsumeBalanceDetails(id), new NetworkObserver<ApiResult<ConsumeDetails>>() {
            @Override
            public void onReady(ApiResult<ConsumeDetails> consumeDetailsApiResult) {
                initConsumeData(consumeDetailsApiResult.getData());
            }
        });
    }

    private void initConsumeData(ConsumeDetails data) {
        mOrderIdTv.setText(data.getId());
        mPriceTv.setText(data.getConsumeValue() / 100 + "消费积分");
        mTimeTv.setText(data.getCreateDate());
        mRemarkTv.setText(data.getRemark());
        if (data.getConsumeType() == 0) {
            caozuo.setText("商城购物");
            status.setText("【使用金额】");
        } else if (data.getConsumeType() == 1) {
            caozuo.setText("转入积分");
            status.setText("【转入金额】");
        }
    }

    private void initBonusData(BonusScoreDetails data) {
        mOrderIdTv.setText(data.getId());
        mPriceTv.setText(data.getBonusValue() / 100 + "奖金积分");
        mTimeTv.setText(data.getCreateDate());
        mRemarkTv.setText(data.getRemark());
        if (data.getBonusType() == 0) {
            caozuo.setText("转入积分");
            status.setText("【转入金额】");
        } else if (data.getBonusType() == 1) {
            caozuo.setText("转出积分");
            status.setText("【转出金额】");
        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    public void back(View view) {
        finish();
    }
}
