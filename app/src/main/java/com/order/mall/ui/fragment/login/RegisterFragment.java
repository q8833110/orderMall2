package com.order.mall.ui.fragment.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.ui.LoginActivity;
import com.order.mall.ui.fragment.BaseFragment;
import com.order.mall.util.CountDownUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterFragment extends BaseFragment {
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_verification)
    EditText etVerification;
    @BindView(R.id.tv_verification)
    TextView tvVerification;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_invitation)
    EditText etInvitation;
    Unbinder unbinder;
    private View rootView;

    private CountDownUtil countDownUtil;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (context instanceof LoginActivity) {
            ((LoginActivity) context).setIvBackShow(true);
            ((LoginActivity) context).setTvTitle("账号注册");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.register, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.tv_verification)
    public void getVerification() {
        if (countDownUtil == null) {
            countDownUtil = new CountDownUtil(tvVerification, 60000, 1000);
        }
        countDownUtil.start();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            if (countDownUtil != null){
                countDownUtil.cancel();
                countDownUtil.reset();
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (countDownUtil != null){
            countDownUtil.onFinish();
        }
    }
}
