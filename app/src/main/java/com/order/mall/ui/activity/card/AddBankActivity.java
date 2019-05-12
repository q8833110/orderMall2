package com.order.mall.ui.activity.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.user.ChangePasswordActivity;
import com.order.mall.ui.adapter.PayMethodAdapter;
import com.order.mall.util.RetrofitUtils;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收款方式
 */
public class AddBankActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_bank)
    EditText bankEdit;
    @BindView(R.id.et_sub_bank)
    EditText subBankEdit;
    @BindView(R.id.et_card)
    EditText cardEdit;
    @BindView(R.id.et_card1)
    EditText card1Edit;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;

    Unbinder unbinder;
    private String id;
    private IUserApi iUserApi;
    private long userId;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        //        userId = SharedPreferencesHelp.getInstance(this).getUser().getId();
        userId = 500000;
        id = getIntent().getStringExtra("id");
        String sName = getIntent().getStringExtra("sName");
        String sBank = getIntent().getStringExtra("sBank");
        String sSubBank = getIntent().getStringExtra("sSubBank");
        String sCard = getIntent().getStringExtra("sCard");
        nameEdit.setText(sName);
        bankEdit.setText(sBank);
        subBankEdit.setText(sSubBank);
        cardEdit.setText(sCard);
        card1Edit.setText(sCard);
        switchButton.setChecked(getIntent().getBooleanExtra("openOrNot", false));
    }

    @OnClick(R.id.tv_ok)
    public void confirm() {
        String sName = nameEdit.getText().toString();
        String sBank = bankEdit.getText().toString();
        String sSubBank = subBankEdit.getText().toString();
        String sCard = cardEdit.getText().toString();
        String sCard1 = card1Edit.getText().toString();
        boolean checked = switchButton.isChecked();
        if (!sName.isEmpty() && !sBank.isEmpty() && !sSubBank.isEmpty() && !sCard.isEmpty() && !sCard1.isEmpty()) {
            if (sCard1.equals(sCard1)) {
                if (id != null) {
                    change(sName, sBank, sSubBank, sCard, checked);
                } else {
                    add(sName, sBank, sSubBank, sCard, checked);

                }
            } else {
                showToast("两次卡号输入不一致");
            }
        } else {
            showToast("请输入完整信息");
        }
    }

    private void add(String sName, String sBank, String sSubBank, String sCard, boolean checked) {
        addObserver(iUserApi.mergeUserPayWayBank(userId, sBank, sName, sSubBank, sCard, checked), new NetworkObserver() {
            @Override
            public void onReady(ApiResult apiResult) {
                if (apiResult.getData() != null) {
                    boolean data = (boolean) apiResult.getData();
                    if (data) {
                        showToast("保存成功");
                        finish();
                    }
                }
            }

        });
    }

    private void change(String sName, String sBank, String sSubBank, String sCard, boolean checked) {
        addObserver(iUserApi.mergeUserPayWayBank2(userId, sBank, sName, sSubBank, sCard, checked, id), new NetworkObserver() {
            @Override
            public void onReady(ApiResult apiResult) {
                if (apiResult.getData() != null) {
                    boolean data = (boolean) apiResult.getData();
                    if (data) {
                        showToast("保存成功");
                        finish();
                    }
                }
            }

        });
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
