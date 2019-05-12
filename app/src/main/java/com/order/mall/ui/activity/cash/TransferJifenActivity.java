package com.order.mall.ui.activity.cash;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 积分转账
 */
public class TransferJifenActivity extends BaseActivity {

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
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_cash)
    TextView tvCash;
    @BindView(R.id.tv_baodan)
    TextView tvBaodan;
    @BindView(R.id.edit_num)
    EditText numEdit;
    @BindView(R.id.tips)
    TextView tips;

    private int type;   //1  现金积分转账  2：奖金积分转账
    private int num;   //全部数量
    private IUserApi iUserApi;
    private int userId = 500000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen_transfer);
        unbinder = ButterKnife.bind(this);

        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);

        type = getIntent().getIntExtra("type", 0);
        num = getIntent().getIntExtra("num", 0);
        init();
        initBonus();
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @OnClick(R.id.all)
    public void all() {
        //全部转出
        numEdit.setText(num + "");
    }

    @OnClick(R.id.tv_ok)
    public void confirm() {
        String sNum = numEdit.getText().toString();
        if (!sNum.isEmpty()) {
            if (type == 1) {
                cashIntoTrade(sNum);
            } else {
                bonusIntoCash(sNum);
            }
        }

    }

    private void bonusIntoCash(String sNum) {
        addObserver(iUserApi.bonusIntoCash(sNum, userId), new NetworkObserver<ApiResult>() {
            @Override
            public void onReady(ApiResult apiResult) {
                boolean data = (boolean) apiResult.getData();
                if (data) {
                    //成功
                    showToast(apiResult.getMessage());
                    finish();
                }
            }
        });
    }

    private void cashIntoTrade(String sNum) {
        addObserver(iUserApi.cashIntoTrade(sNum, userId), new NetworkObserver<ApiResult>() {


            @Override
            public void onReady(ApiResult apiResult) {
                boolean data = (boolean) apiResult.getData();
                if (data) {
                    //成功
                    showToast(apiResult.getMessage());
                    finish();
                }
            }
        });
    }

    private void initBonus() {
        if (type == 2) {
            tvCash.setBackgroundResource(R.drawable.cash_transfer);
            tvCash.setText("奖金积分");
            tvBaodan.setBackgroundResource(R.drawable.bonus_transfer);
            tvBaodan.setText("现金积分");
        }
    }

    private void init() {
        tvTitle.setText("积分转账");
        if (type == 1) {
            tvYue.setText("可用余额：" + num + "现金积分");
            tips.setText("转出到报单积分数");
        } else if (type == 2) {
            tvYue.setText("可用余额：" + num + "奖金积分");
            tips.setText("转出到现金积分数");

        }

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
