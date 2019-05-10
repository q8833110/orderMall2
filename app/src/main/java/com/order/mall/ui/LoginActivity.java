package com.order.mall.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.ILoginApi;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.login.UserRespDTO;
import com.order.mall.data.network.login.VerifyCodeReqDTO;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.fragment.login.LoginFragment;
import com.order.mall.ui.fragment.login.MobileLoginFragment;
import com.order.mall.ui.fragment.login.RegisterFragment;
import com.order.mall.util.RetrofitUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class LoginActivity extends BaseActivity {
    public static final int LOGIN_MAIN = 01;
    public static final int LOGIN_MOBILE = 02;
    public static final int REGISTER = 03;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    FragmentManager fragmentManager ;

    private ILoginApi loginApi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        loginApi = RetrofitUtils.getInstance().getRetrofit().create(ILoginApi.class);
        fragmentManager = getSupportFragmentManager() ;
        toFragment(LOGIN_MAIN);
        //TODO 测试Git版本控制

    }

    /**
     * 获取验证码
     */
    public void getRegisterVerifyCode(int type , String mobile , final String tag ){
        addObserver(loginApi.getVerifyCode(type , mobile ),new NetworkObserver<ApiResult<String>>(){

            @Override
            public void onReady(ApiResult<String> stringApiResult) {
                showToast(stringApiResult.getMessage());
                if (stringApiResult.getCode() == 0){
                    if (MobileLoginFragment.class.getSimpleName().equals(tag)){
                        ((MobileLoginFragment)fragmentManager.findFragmentByTag(tag)).startCountDown();
                    }else if (RegisterFragment.class.getSimpleName().equals(tag)){
                        ((RegisterFragment)fragmentManager.findFragmentByTag(tag)).startDown();
                    }
                }
            }
        });
    }

    /**
     * 获取验证码
     * @param mobile
     * @param verifycode
     */
    public void login(String mobile , String verifycode){
        addObserver(loginApi.mobileLogin(mobile , verifycode) , new NetworkObserver<ApiResult<UserRespDTO>>(){

            @Override
            public void onReady(ApiResult<UserRespDTO> userRespDTOApiResult) {
                showToast(userRespDTOApiResult.getMessage());
                if (userRespDTOApiResult.getData() != null) {
                    SharedPreferencesHelp.getInstance(LoginActivity.this).putUser(userRespDTOApiResult.getData());
                    toMain();
                }
            }
        } );
    }

    public void register(String account , String mobile , String verifyCode , String password , String parentId){
        addObserver(loginApi.register(account , mobile , verifyCode , password , parentId) , new NetworkObserver<ApiResult<String>>(){

            @Override
            public void onReady(ApiResult<String> userRespDTOApiResult) {
                showToast(userRespDTOApiResult.getMessage());
                if (userRespDTOApiResult.getCode() == 0){
                    toFragment(LOGIN_MAIN);
                }
            }
        } );
    }

    /**
     * 获取验证码
     * @param mobile
     * @param verifycode
     */
    public void loginAccount(String mobile , String verifycode){
        addObserver(loginApi.Accountlogin(mobile , verifycode) , new NetworkObserver<ApiResult<UserRespDTO>>(){

            @Override
            public void onReady(ApiResult<UserRespDTO> userRespDTOApiResult) {
                if (userRespDTOApiResult.getData() != null) {
                    SharedPreferencesHelp.getInstance(LoginActivity.this).putUser(userRespDTOApiResult.getData());
                    toMain();
                }else{
                    showToast(userRespDTOApiResult.getMessage());
                }
            }
        } );
    }

    private void toMain(){

        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void setIvBackShow(boolean canShow) {
        if (canShow) {
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.GONE);
        }
    }

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()
                .init();
    }

    @OnClick(R.id.back)
    public void back(){
        toFragment(LOGIN_MAIN);
    }

    public void toFragment(int fragmentType) {
        if (fragmentManager == null) fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction() ;
        hideAllFragment(fragmentTransaction);
        Fragment fragment ;
        switch (fragmentType){
            case LOGIN_MAIN:
                fragment = fragmentManager.findFragmentByTag(LoginFragment.class.getSimpleName());
                if (fragment == null){
                    fragment = LoginFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment , fragment ,LoginFragment.class.getSimpleName());
                }else{
                    fragmentTransaction.show(fragment);
                }
                break ;

            case LOGIN_MOBILE:
                fragment = fragmentManager.findFragmentByTag(MobileLoginFragment.class.getSimpleName());
                if (fragment == null){
                    fragment = MobileLoginFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment , fragment , MobileLoginFragment.class.getSimpleName());
                }else{
                    fragmentTransaction.show(fragment);
                }
                break ;

            case REGISTER:
                fragment = fragmentManager.findFragmentByTag(RegisterFragment.class.getSimpleName());
                if (fragment == null){
                    fragment = RegisterFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment , fragment , RegisterFragment.class.getSimpleName());
                }else{
                    fragmentTransaction.show(fragment);
                }
                break ;

            default:
                fragment = fragmentManager.findFragmentByTag(LoginFragment.class.getSimpleName());
                if (fragment == null){
                    fragment = LoginFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment , fragment);
                }else{
                    fragmentTransaction.show(fragment);
                }
                break ;
        }

        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        Fragment loginFragment = fragmentManager.findFragmentByTag(LoginFragment.class.getSimpleName());
        if (loginFragment != null){
            fragmentTransaction.hide(loginFragment);
        }
        Fragment mobileLoginFragment = fragmentManager.findFragmentByTag(MobileLoginFragment.class.getSimpleName());
        if (mobileLoginFragment != null){
            fragmentTransaction.hide(mobileLoginFragment);
        }
        Fragment registerFragment = fragmentManager.findFragmentByTag(RegisterFragment.class.getSimpleName());
        if (registerFragment != null){
            fragmentTransaction.hide(registerFragment);
        }
    }
}
