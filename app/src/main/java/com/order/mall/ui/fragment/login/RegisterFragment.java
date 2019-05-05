package com.order.mall.ui.fragment.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        init();
        return rootView;
    }

    @OnClick(R.id.tv_verification)
    public void getVerification() {

        String mobile = etMobile.getText().toString() ;
        if (TextUtils.isEmpty(mobile)){
            Toast.makeText(getContext() , "手机号不能为空" , Toast.LENGTH_LONG).show();
            return ;
        }
        if (context instanceof  LoginActivity){
            ((LoginActivity) context).getRegisterVerifyCode(1 ,mobile , RegisterFragment.class.getSimpleName());
        }
    }

    public void startDown(){
        if (countDownUtil != null) countDownUtil.start();
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


    @OnClick(R.id.register)
    public void register(){
        String account = etAccount.getText().toString() ;
        String mobile = etMobile.getText().toString();
        String verifyCode = etVerification.getText().toString();
        String password = etPassword.getText().toString() ;
        String parentId = etInvitation.getText().toString();
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(verifyCode) ||
        TextUtils.isEmpty(verifyCode) || TextUtils.isEmpty(parentId) || TextUtils.isEmpty(password)
        ){
            Toast.makeText(getContext() , "请全部都要填写" , Toast.LENGTH_LONG).show();
            return ;
        }
        if (context instanceof LoginActivity) {
            ((LoginActivity) context).register(account , mobile , verifyCode , password , parentId);
        }
    }
    @Override
    public void init() {
        if (countDownUtil == null) {
            countDownUtil = new CountDownUtil(tvVerification, 60000, 1000);
        }
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
