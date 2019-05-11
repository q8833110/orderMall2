package com.order.mall.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.order.mall.data.network.homepage.Home;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.widget.Dialog.LoadingDialog;

import java.util.List;

import retrofit2.HttpException;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class LazyLoadFragment extends Fragment {
    protected static final String TAG = LazyLoadFragment.class.getSimpleName();
    private boolean isViewCreated; // 界面是否已创建完成
    private boolean isVisibleToUser; // 是否对用户可见
    private boolean isDataLoaded; // 数据是否已请求, isNeedReload()返回false的时起作用
    private boolean isHidden = true; // 记录当前fragment的是否隐藏

    // 实现具体的数据请求逻辑
    protected abstract void loadData();

    public CompositeSubscription subscriptions;

    /**
     * 使用ViewPager嵌套fragment时，切换ViewPager回调该方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        tryLoadData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new CompositeSubscription();
    }

    /**
     * 使用show()、hide()控制fragment显示、隐藏时回调该方法
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidden = hidden;
        if (!hidden) {
            tryLoadData1();
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

    /**
     * ViewPager场景下，判断父fragment是否可见
     *
     * @return
     */
    private boolean isParentVisible() {
        Fragment fragment = getParentFragment();
        return fragment == null || (fragment instanceof LazyLoadFragment && ((LazyLoadFragment) fragment).isVisibleToUser);
    }

    /**
     * ViewPager场景下，当前fragment可见，如果其子fragment也可见，则尝试让子fragment加载请求
     */
    private void dispatchParentVisibleState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof LazyLoadFragment && ((LazyLoadFragment) child).isVisibleToUser) {
                ((LazyLoadFragment) child).tryLoadData();
            }
        }
    }

    /**
     * fragment再次可见时，是否重新请求数据，默认为flase则只请求一次数据
     *
     * @return
     */
    protected boolean isNeedReload() {
        return false;
    }

    /**
     * ViewPager场景下，尝试请求数据
     */
    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && isParentVisible() && (isNeedReload() || !isDataLoaded)) {
            loadData();
            isDataLoaded = true;
            dispatchParentVisibleState();
        }
    }

    /**
     * show()、hide()场景下，当前fragment没隐藏，如果其子fragment也没隐藏，则尝试让子fragment请求数据
     */
    private void dispatchParentHiddenState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof LazyLoadFragment && !((LazyLoadFragment) child).isHidden) {
                ((LazyLoadFragment) child).tryLoadData1();
            }
        }
    }

    /**
     * show()、hide()场景下，父fragment是否隐藏
     *
     * @return
     */
    private boolean isParentHidden() {
        Fragment fragment = getParentFragment();
        if (fragment == null) {
            return false;
        } else if (fragment instanceof LazyLoadFragment && !((LazyLoadFragment) fragment).isHidden) {
            return false;
        }
        return true;
    }

    /**
     * show()、hide()场景下，尝试请求数据
     */
    public void tryLoadData1() {
        if (!isParentHidden() && (isNeedReload() || !isDataLoaded)) {
            loadData();
            isDataLoaded = true;
            dispatchParentHiddenState();
        }
    }

    @Override
    public void onDestroy() {
        isViewCreated = false;
        isVisibleToUser = false;
        isDataLoaded = false;
        isHidden = true;
        super.onDestroy();
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

    private LoadingDialog mProgressDialog;

    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingDialog(getContext());
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
}
