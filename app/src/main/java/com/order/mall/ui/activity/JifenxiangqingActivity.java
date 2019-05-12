package com.order.mall.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.TradeDetails;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JifenxiangqingActivity extends BaseActivity {

    @BindView(R.id.order_id)
    TextView mOrderIdTv;
    @BindView(R.id.time)
    TextView mTimeTv;
    @BindView(R.id.tv_price)
    TextView mPriceTv;
    @BindView(R.id.tv_beizhu)
    TextView mRemarkTv;

    private IUserApi iUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_jifenxiangqing);
        ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);

        String id = getIntent().getStringExtra("id");
        getDetails(id);

    }

    private void getDetails(String id) {
        addObserver(iUserApi.oneTradeBalanceDetails(id), new NetworkObserver<ApiResult<TradeDetails>>() {
            @Override
            public void onReady(ApiResult<TradeDetails> tradeDetailsApiResult) {
                initData(tradeDetailsApiResult.getData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void initData(TradeDetails data) {
        mOrderIdTv.setText(data.getId());
        mPriceTv.setText(String.valueOf(data.getTradeValue()));
        mTimeTv.setText(data.getCreateDate());
        mRemarkTv.setText(data.getRemark());
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
