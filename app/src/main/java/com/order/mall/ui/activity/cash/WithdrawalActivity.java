package com.order.mall.ui.activity.cash;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.WithdrawalAdapter;
import com.order.mall.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提现
 */
public class WithdrawalActivity extends BaseActivity {

    Unbinder unbinder;

    Dialog dialog;

    WithdrawalAdapter WxAdapeter;

    WithdrawalAdapter ZhifubaoAdapeter;

    WithdrawalAdapter BankAdapeter;
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
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_equal_grade)
    TextView tvEqualGrade;
    @BindView(R.id.tip)
    TextView tip;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.rl_chose_withdrawal)
    RelativeLayout rlChoseWithdrawal;
    @BindView(R.id.pay)
    TextView pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        unbinder = ButterKnife.bind(this);
        init();
        initDialog();
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    private void initDialog() {
        List<WithdrawalAdapter.Data> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new WithdrawalAdapter.Data());
        }
        if (dialog == null) {
            dialog = new Dialog(this, R.style.BottomDialogStyle);
            View view = View.inflate(this, R.layout.dialog_withdrawal, null);
            TextView mCancel = view.findViewById(R.id.tv_cancel);
            TextView tOk = view.findViewById(R.id.ok);
            RecyclerView rvBank = view.findViewById(R.id.rv_bank);
            RecyclerView rvWx = view.findViewById(R.id.rv_wx);
            RecyclerView rvZhifubao = view.findViewById(R.id.rv_zhifubao);
            WxAdapeter = new WithdrawalAdapter(WithdrawalActivity.this, R.layout.item_withdrawal_amount, dataList);
            ZhifubaoAdapeter = new WithdrawalAdapter(WithdrawalActivity.this, R.layout.item_withdrawal_amount, dataList);
            BankAdapeter = new WithdrawalAdapter(WithdrawalActivity.this, R.layout.item_withdrawal_amount, dataList);
            rvBank.setAdapter(BankAdapeter);
            rvWx.setAdapter(WxAdapeter);
            rvZhifubao.setAdapter(ZhifubaoAdapeter);
            rvBank.setLayoutManager(new LinearLayoutManager(this));
            rvWx.setLayoutManager(new LinearLayoutManager(this));
            rvZhifubao.setLayoutManager(new LinearLayoutManager(this));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(true);
//        view.setMinimumHeight((int) (ScreenUtils.getScreenHeight() * 0.23f));
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = (int) (ScreenUtils.getScreenWidth(this));
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            dialogWindow.setAttributes(lp);
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            tOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
    }

    private void init() {
        tvTitle.setText("提现");
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    @OnClick(R.id.rl_chose_withdrawal)
    public void choseWitdrawalAmount() {
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
