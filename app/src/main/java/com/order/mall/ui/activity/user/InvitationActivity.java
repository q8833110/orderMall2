package com.order.mall.ui.activity.user;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.AppUrlInfo;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.FileUtils;
import com.order.mall.util.RetrofitUtils;
import com.order.mall.util.ScreenUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvitationActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.down)
    TextView down;

    private IUserApi userApi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        ButterKnife.bind(this);
        init();
        addNet();
    }

    private void init(){
        userApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
    }
    private String imageUrl ;
    private void addNet(){
        addObserver(userApi.getAppUrlInfo("1111") , new NetworkObserver<ApiResult<AppUrlInfo>>(){
            @Override
            public void onReady(ApiResult<AppUrlInfo> appUrlInfoApiResult) {
                if (appUrlInfoApiResult.getData() != null) {
                    AppUrlInfo appUrlInfo = appUrlInfoApiResult.getData();
                    tvCode.setText(appUrlInfo.getValue() +"");
                    String key = appUrlInfo.getKey() ;
                    if (!TextUtils.isEmpty(key)){
                        AppUrlInfo.QrCode qrCode = new Gson().fromJson(key , AppUrlInfo.QrCode.class);
                        if (qrCode != null) {
                            imageUrl = qrCode.getQrcode();
                            Log.e(TAG, "onReady: " + qrCode.getQrcode() );
                        }

                    }
                    Glide.with(InvitationActivity.this).load(imageUrl).into(ivQrcode);
                }else{
                    showToast(appUrlInfoApiResult.getMessage());
                }
            }
        });
    }



    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @OnClick(R.id.down)
    public void dorwn(){
        FilePermission();
    }

    @Override
    public void downFile() {
        super.downFile();
        int width = (ivQrcode == null ?ScreenUtils.dpToPxInt(this , 102) : ivQrcode.getWidth()) ;
        int height = (ivQrcode == null ?ScreenUtils.dpToPxInt(this , 102) : ivQrcode.getHeight()) ;
        if (!TextUtils.isEmpty(imageUrl)){
            final String file = Environment.getExternalStorageDirectory() + File.separator+"orderMall" + File.separator + "qrcode"+ System.currentTimeMillis() + ".jpg";
            Glide.with(this)
                    .load(imageUrl)
                    .asBitmap()
                    .toBytes()
                    .into(new SimpleTarget<byte[]>(width, height) {
                        @Override
                        public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                            // 下载成功回调函数
                            // 数据处理方法，保存bytes到文件
                            if (FileUtils.copy(file, bytes)){
                                showToast("下载成功");
                            }else{
                                showToast("下载失败");
                            }
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            // 下载失败回调
                            showToast("下载失败");
                        }
                    });
        }
    }

    @OnClick(R.id.back)
    public void back(){
        this.finish();
    }
}
