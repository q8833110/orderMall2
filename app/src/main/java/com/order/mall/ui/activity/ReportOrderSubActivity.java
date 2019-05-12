package com.order.mall.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IBaseApi;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.RechargeDetails;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.MainActivity;
import com.order.mall.ui.activity.user.SettingActivity;
import com.order.mall.ui.widget.Dialog.ActionSheetDialog;
import com.order.mall.util.RetrofitUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.provider.MediaStore.Images.Media.getBitmap;
import static com.order.mall.ui.activity.user.SettingActivity.getRealPathFromUri;

/**
 * 订单提交
 */
public class ReportOrderSubActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.pay_style)
    TextView payStyle;
    @BindView(R.id.iv_pay_style)
    ImageView ivPayStyle;
    @BindView(R.id.payment_type)
    TextView paymentType;
    @BindView(R.id.iv_amount)
    ImageView ivAmount;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.payment_qrcode)
    TextView paymentQrcode;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.pay1)
    ImageView pay1;
    @BindView(R.id.pay2)
    ImageView pay2;

    Unbinder unbinder;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.add1)
    ImageView add1;
    @BindView(R.id.delete2)
    ImageView delete2;
    @BindView(R.id.rl_add1)
    RelativeLayout rlAdd1;
    @BindView(R.id.add2)
    ImageView add2;
    @BindView(R.id.delete1)
    ImageView delete1;
    @BindView(R.id.rl_add2)
    RelativeLayout rlAdd2;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_already_pay)
    TextView tvAlreadyPay;
    private IUserApi iUserApi;
    private String orderId;

    private int choseImage = -1;

    private Uri mPhotoImageUri;
    private Uri mPickImageUri;
    private Uri mCropImageUri;

    private IBaseApi baseApi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_order_submit);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        orderId = getIntent().getStringExtra("id");
        tvTitle.setText("订单详情");
        baseApi = RetrofitUtils.getInstance().getRetrofit().create(IBaseApi.class);
        getDetails();
    }

    private void getDetails() {
        addObserver(iUserApi.rechargeDetails(orderId), new NetworkObserver<ApiResult<RechargeDetails>>() {


            @Override
            public void onReady(ApiResult<RechargeDetails> rechargeDetailsApiResult) {
                if (rechargeDetailsApiResult.getData() != null) {
                    init(rechargeDetailsApiResult.getData());

                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }
    private  RechargeDetails data ;
    private void init(RechargeDetails data) {
        this.data = data ;
        id.setText(data.getId());
        price.setText(data.getAmount() + "");
        time.setText(data.getCreateTime());
        payStyle.setText(data.getPayWayName());
        paymentType.setText(data.getPayWayName());
        amount.setText(data.getToAccount());
        name.setText(data.getToUser());
        Glide.with(this).load(data.getToQrCode()).into(ivQrcode);
        String proofOfPay = data.getProofOfPay();
//        if (proofOfPay.contains(";")) {
//            String[] split = proofOfPay.split(";");
//            Glide.with(this).load(split[0]).into(pay1);
//            Glide.with(this).load(split[1]).into(pay2);
//
//        } else {
//            Glide.with(this).load(proofOfPay).into(pay1);
//        }
//        if (data.getPayWayValue() == 1) {
//            //微信
//            Glide.with(this).load(R.drawable.weixin).into(ivPayStyle);
//        } else if (data.getPayWayValue() == 2) {
//            //支付宝
//            Glide.with(this).load(R.drawable.zhifubao).into(ivPayStyle);
//
//        } else if (data.getPayWayValue() == 3) {
//            //银行卡
//            Glide.with(this).load(R.drawable.bank).into(ivPayStyle);
//
//        }
    }

    @OnClick(R.id.tv_cancel)
    public void cancel() {
        //取消订单
        addObserver(iUserApi.topUpCancle(orderId), new NetworkObserver() {
            @Override
            public void onReady(ApiResult apiResult) {
                if (apiResult.getData() != null) {
                    boolean data = (boolean) apiResult.getData();
                    if (data) {
                        //成功
                        showToast("取消充值成功");
                        finish();
                    }
                }
            }
        });

    }

    @OnClick(R.id.tv_already_pay)
    public void pay() {
        Intent intent = new Intent(this, RechargeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private Uri getCropUri(String fileName) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xiaolian/";
        File path = new File(filePath);
        if (!path.exists() && !path.mkdirs()) {
            showToast("没有SD卡权限");
            return null;
        }
        File outputImage = new File(path, fileName + ".jpg");
        try {
            if (outputImage.exists() && !outputImage.delete()) {
                showToast("没有SD卡权限");
                return null;
            }
            if (!outputImage.createNewFile()) {
                showToast("没有SD卡权限");
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return Uri.fromFile(outputImage);
    }

    ActionSheetDialog actionSheetDialog;

    private static final int REQUEST_CODE_CAMERA = 0x1103;
    private static final int REQUEST_CODE_PICK = 0x1104;
    private static final int REQUEST_CODE_CROP = 0x1105;

    @OnClick({R.id.rl_add1, R.id.rl_add2})
    public void choseHead(View view) {
        switch (view.getId()) {
            case R.id.rl_add1:
                choseImage = 1;
                break;
            case R.id.rl_add2:
                choseImage = 2;
                break;
                default:
                    choseImage = 1 ;
        }
        if (actionSheetDialog == null) {
            actionSheetDialog = new ActionSheetDialog(this)
                    .builder()
                    .setTitle("选择")
                    .addSheetItem("相机", ActionSheetDialog.SheetItemColor.Orange,
                            i -> rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                                    .subscribe(granted -> {
                                        if (granted) {
                                            takePhoto();
                                        } else {
                                            showToast("没有相机权限");
                                        }
                                    }))
                    .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Orange,
                            i -> rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(granted -> {
                                        if (granted) {
                                            selectPhoto();
                                        } else {
                                            showToast("没有SD卡权限");
                                        }
                                    }));
            actionSheetDialog.show();
        } else {
            actionSheetDialog.show();
        }
    }

    File outputImage;

    private void selectPhoto() {
        mPickImageUri = getImageUri("pick");
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK);
    }

    private void takePhoto() {
        mPhotoImageUri = getImageUri("photo");
        //调用相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoImageUri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * @param activity    当前activity
     * @param orgUri      剪裁原图的Uri
     * @param desUri      剪裁后的图片的Uri
     * @param aspectX     X方向的比例
     * @param aspectY     Y方向的比例
     * @param width       剪裁图片的宽度
     * @param height      剪裁图片高度
     * @param requestCode 剪裁图片的请求码
     */
    public static void cropImageUri(Activity activity, Uri orgUri, Uri desUri, int aspectX, int aspectY, int width, int height, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, requestCode);
    }

    private Uri getImageUri(String fileName) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/orderMall/";
        File path = new File(filePath);
        if (!path.exists()) {
            boolean isPathSuccess = path.mkdirs();
            if (!isPathSuccess) {
                showToast("没有SD卡权限");
                return null;
            }
        }
        Uri imageUri;
        outputImage = new File(path, fileName + System.currentTimeMillis() + ".jpg");
        try {
            if (outputImage.exists()) {
                boolean isDeleteSuccess = outputImage.delete();
                if (!isDeleteSuccess) {
                    showToast("没有SD卡权限");
                    return null;
                }
            }
            if (!outputImage.createNewFile()) {
                showToast("没有SD卡权限");
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(this, "com.order.mall", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        return imageUri;
    }

    private String imagePath1;
    private String imagePath2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480, output_Y = 480;
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAMERA) {
                mCropImageUri = getCropUri("crop");
                cropImageUri(this, mPhotoImageUri, mCropImageUri, 1, 1, output_X, output_Y, REQUEST_CODE_CROP);
            } else if (requestCode == REQUEST_CODE_PICK) {
                mCropImageUri = getCropUri("crop");
                cropImageUri(this, data.getData(), mCropImageUri, 1, 1, output_X, output_Y, REQUEST_CODE_CROP);
            } else if (requestCode == REQUEST_CODE_CROP) {
               String imagePath = getRealPathFromUri(this, mCropImageUri);
               uploadFile(imagePath);
            }
        }
    }

    public void uploadFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return;
        RequestBody requestBody;
        MultipartBody.Builder builder;
        builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("imgs", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        requestBody = builder.build();
        addObserver(baseApi.upload(requestBody), new NetworkObserver<ApiResult<List<String>>>() {
            @Override
            public void onReady(ApiResult<List<String>> stringApiResult) {
                if (stringApiResult.getData() != null && stringApiResult.getData().size() > 0) {
                    List<String> urls = stringApiResult.getData();
                    if (choseImage == 1) {
                        Glide.with(ReportOrderSubActivity.this).load(urls.get(0)).into(pay1);
                        imagePath1 = urls.get(0);
                    }
                    else {
                        Glide.with(ReportOrderSubActivity.this).load(urls.get(0)).into(pay2);
                        imagePath2 = urls.get(0);
                    }
                    referDelete();
                } else {
                    showToast(stringApiResult.getMessage());
                }
            }
        });
    }

    private void referDelete(){
        if (!TextUtils.isEmpty(imagePath1)){
            delete2.setVisibility(View.VISIBLE);
        }else{
            delete2.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(imagePath2)){
            delete1.setVisibility(View.VISIBLE);
        }else{
            delete1.setVisibility(View.GONE);
        }
        referAdd2Bg();
    }

    @OnClick(R.id.tv_already_pay)
    public void confirm(){
        if (TextUtils.isEmpty(imagePath1) && TextUtils.isEmpty(imagePath2)){
            showToast("请上传凭证照片");
            return  ;
        }
        String imgs = imagePath1 +";" + imagePath2 ;
        addObserver(iUserApi.topConfirm(data.getId(), imgs), new NetworkObserver<ApiResult<String>>() {
            @Override
            public void onReady(ApiResult<String> apiResult) {
                if (apiResult.getData() != null){
                    showToast("充值成功");
                    Intent intent = new Intent(ReportOrderSubActivity.this , MainActivity.class);
                    startActivity(intent);
                }else{
                    showToast(apiResult.getMessage());
                }
            }
        });
    }

    @OnClick(R.id.delete1)
    public void delete(){
        imagePath2 = "" ;
        pay2.setImageBitmap(null);
        referDelete();

    }

    @OnClick(R.id.delete2)
    public void delete2(){
        imagePath1 = "" ;
        pay1.setImageBitmap(null);
        referDelete();
    }

    public void referAdd2Bg(){
        if (TextUtils.isEmpty(imagePath2) && !TextUtils.isEmpty(imagePath1)) {
            rlAdd2.setBackgroundColor(getResources().getColor(R.color.color1));
        }else if (TextUtils.isEmpty(imagePath1)){
            rlAdd2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        }
    }
}
