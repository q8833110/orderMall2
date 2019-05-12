package com.order.mall.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.AddressAdapter;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_old_pwd)
    EditText mOldPwdEdit;
    @BindView(R.id.et_new_pwd)
    EditText mNewPwdEdit;
    @BindView(R.id.et_new_pwd2)
    EditText mNewPwdEdit2;

    Unbinder unbinder;
    AddressAdapter adapter;
    private IUserApi iUserApi;
    private int userId = 500000;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
    }

    public void confirm(View view) {
        String soldPwd = mOldPwdEdit.getText().toString();
        String sNewPwd = mNewPwdEdit.getText().toString();
        String sNewPwd2 = mNewPwdEdit2.getText().toString();

        if (!soldPwd.isEmpty() && !sNewPwd.isEmpty() && !sNewPwd2.isEmpty()) {
            changePwd(soldPwd, sNewPwd, sNewPwd2);
        }
    }

    private void changePwd(String soldPwd, String sNewPwd, String sNewPwd2) {
        addObserver(iUserApi.changePwd(userId, soldPwd, sNewPwd, sNewPwd2), new NetworkObserver<ApiResult>() {


            @Override
            public void onReady(ApiResult apiResult) {
                showToast(apiResult.getMessage());
                boolean data = (boolean) apiResult.getData();
                if (data) {
                    finish();
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
