package com.order.mall.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.AddressAdapter;
import com.order.mall.widget.MyDialog;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddAddressActivity extends BaseActivity implements MyDialog.OkDialogListener,
        MyDialog.CancelDialogListener {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    Unbinder unbinder;

    AddressAdapter adapter;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.delete)
    TextView delete;

    private MyDialog dialog;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        unbinder = ButterKnife.bind(this);
        init();
    }



    private void init() {
        dialog = new MyDialog(this);
    }

    private void showDelete(){
        delete.setVisibility(View.VISIBLE);
    }

    private void hideDelete(){
        delete.setVisibility(View.GONE);
    }

    @OnClick(R.id.tv_ok)
    public void tvOk() {
        dialog.show();
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

    @Override
    public void cancel() {

    }

    @Override
    public void ok() {

    }
}
