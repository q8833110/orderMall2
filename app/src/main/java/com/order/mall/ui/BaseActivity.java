package com.order.mall.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.widget.Dialog.LoadingDialog;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.HttpException;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {
    protected static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    SharedPreferencesHelp sharedPreferencesHelp;

    // 统一管理observer，防止内存泄露
    public CompositeSubscription subscriptions;

    private LoadingDialog mProgressDialog;

    protected RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new CompositeSubscription();
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        rxPermissions = RxPermissions.getInstance(this);
    }

    public void FilePermission() {
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            downFile();
                        } else {
                            showToast("读写文件权限");
                        }
                    }
                });
    }


    public void downFile() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void setUp() {
    }


    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 清除事件
        clearObservers();
    }


    /**
     * 启动activity完成跳转
     *
     * @param activity 当前activity
     * @param clazz    目标activity class
     */
    public void startActivity(AppCompatActivity activity, Class<?> clazz) {
        startActivity(activity, clazz, null);
    }

    /**
     * 启动activity完成跳转
     *
     * @param activity 当前activity
     * @param clazz    目标activity class
     * @param extraMap 捆绑参数
     */
    public void startActivity(AppCompatActivity activity, Class<?> clazz, Map<String, ? extends Serializable> extraMap) {
        Intent intent = new Intent();
        intent.setClass(activity, clazz);
        if (null != extraMap) {
            for (Map.Entry<String, ? extends Serializable> entry : extraMap.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        startActivity(intent);
    }

    //    ============== totast
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }


//    ===================== 网路请求 observable


    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingDialog(this);
        }
        try {
            hideLoading();
            mProgressDialog.show();
        } catch (Exception e) {
            Log.wtf(TAG, "showLoading出错", e);
        }
    }

    public void hideLoading() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        } catch (Exception e) {
            Log.wtf(TAG, "hideLoading出错", e);
        }
    }

    public <P> void addObserver(Observable<P> observable, NetworkObserver observer) {
        if (null != subscriptions) {
            this.subscriptions.add(
                    observable
                            .compose(this.applySchedulers())
                            .subscribe(observer));
        }
    }

    public <P> void addObserver(Observable<P> observable, NetworkObserver observer, Scheduler scheduler) {
        if (null != subscriptions) {
            this.subscriptions.add(
                    observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(scheduler)
                            .subscribe(observer));
        }
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void clearObservers() {
        if (null != subscriptions && !subscriptions.isUnsubscribed()) {
            // handler.post(() -> {
            subscriptions.unsubscribe();
            subscriptions.clear();
            // });
        }
    }

    // 重置订阅列表集合
    public void resetSubscriptions() {
        clearObservers();
        subscriptions = new CompositeSubscription();
    }


    public void onBizCodeError(Error error) {

    }


    public abstract class NetworkObserver<T extends ApiResult> extends Subscriber<T> {

        private boolean renderView = true;
        // 用于列表页token失效后跳转到登录页
        private boolean checkHttpError = false;

        public NetworkObserver(boolean renderView) {
            this.renderView = renderView;
        }

        public NetworkObserver(boolean renderView, boolean checkHttpError) {
            this.renderView = renderView;
            this.checkHttpError = checkHttpError;
        }

        public NetworkObserver() {

        }

        public void onRemoteInvocationError(Throwable e) {

        }

        @Override
        public void onStart() {
            super.onStart();
            if (renderView) {
                showLoading();
            }
        }

        @Override
        public void onNext(T t) {
//            Integer code = t.getCode();
//            if (null != error) {
//                onBizCodeError(error);
//            }
            onReady(t);
            if (renderView) {
                hideLoading();
            }
        }

        public abstract void onReady(T t);

        @Override
        public void onError(Throwable e) {
            try {
                Log.wtf(TAG, e);
                if (renderView) {
                    onRemoteInvocationError(e);
                    hideLoading();
                }
                if (checkHttpError) {
                    // 处理toke失效需要重新登录的错误
                    onHttpError(e);
                }
            } catch (Exception e1) {
                Log.e(TAG, e1.getLocalizedMessage());
            }
        }

        @Override
        public void onCompleted() {
            if (renderView) {
                hideLoading();
            }
        }
    }

    private void onHttpError(Throwable e) {
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                default:
                    break;
            }
        }
    }
}
