package com.order.mall.ui.fragment.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.ui.LoginActivity;
import com.order.mall.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.mobile_login)
    TextView mobileLogin;
    @BindView(R.id.register)
    TextView register;
    Unbinder unbinder;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (context instanceof LoginActivity) {
            ((LoginActivity) context).setIvBackShow(false);
            ((LoginActivity) context).setTvTitle("登陆");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_login , container , false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick(R.id.login)
    public void login(){

    }

    @OnClick(R.id.register)
    public void register(){
        if (context instanceof  LoginActivity){
            ((LoginActivity)context).toFragment(LoginActivity.REGISTER);
        }
    }

    @OnClick(R.id.mobile_login)
    public void mobileLogin(){
        if (context instanceof  LoginActivity){
            ((LoginActivity)context).toFragment(LoginActivity.LOGIN_MOBILE);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void initView(View view) {

    }
}
