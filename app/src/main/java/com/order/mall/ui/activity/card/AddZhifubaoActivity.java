package com.order.mall.ui.activity.card;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.RetrofitUtils;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收款方式
 */
public class AddZhifubaoActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_bank)
    EditText accountEdit;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;

    Unbinder unbinder;
    private IUserApi iUserApi;
    private long userId;
    private String sName;
    private String sAccount;
    private String id;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifubao);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        userId = SharedPreferencesHelp.getInstance(this).getUser().getId();
        String sName = getIntent().getStringExtra("sName");
        String sAccount = getIntent().getStringExtra("sAccount");
        boolean openOrNot = getIntent().getBooleanExtra("openOrNot", false);
        id = getIntent().getStringExtra("id");

        nameEdit.setText(sName);
        accountEdit.setText(sAccount);
        switchButton.setChecked(openOrNot);

    }

    @OnClick(R.id.tv_ok)
    public void confirm() {
        sName = nameEdit.getText().toString();
        sAccount = accountEdit.getText().toString();
        boolean checked = switchButton.isChecked();
        if (!sName.isEmpty() && !sAccount.isEmpty()) {
            if (id != null) {
                change(sName, sAccount, checked);
            } else {
                add(sName, sAccount, checked);
            }

        }
    }

    private void add(String sName, String sAccount, boolean checked) {
        addObserver(iUserApi.mergeUserPayWayAli2(userId, sAccount, sName, checked, id), new NetworkObserver() {
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

    private void change(String sName, String sAccount, boolean checked) {
        addObserver(iUserApi.mergeUserPayWayAli2(userId, sAccount, sName, checked, id), new NetworkObserver() {
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
