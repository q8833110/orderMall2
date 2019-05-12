package com.order.mall.ui.activity.user;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
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
import com.order.mall.data.network.login.UserRespDTO;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.AddressAdapter;
import com.order.mall.ui.widget.Dialog.ActionSheetDialog;
import com.order.mall.util.RetrofitUtils;
import com.order.mall.util.VersionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SettingActivity extends BaseActivity {

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
    @BindView(R.id.iv_head_right)
    ImageView ivHeadRight;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_name_right)
    ImageView ivNameRight;
    @BindView(R.id.iv_name)
    TextView ivName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.iv_mobile_right)
    ImageView ivMobileRight;
    @BindView(R.id.iv_mobile)
    TextView ivMobile;
    @BindView(R.id.rl_mobile)
    RelativeLayout rlMobile;
    @BindView(R.id.iv_password_right)
    ImageView ivPasswordRight;
    @BindView(R.id.iv_password)
    TextView ivPassword;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rl_version)
    RelativeLayout rlVersion;

    private UserRespDTO user;

    ActionSheetDialog actionSheetDialog;

    private static final int REQUEST_CODE_CAMERA = 0x1103;
    private static final int REQUEST_CODE_PICK = 0x1104;
    private static final int REQUEST_CODE_CROP = 0x1105;

    private IBaseApi baseApi;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        baseApi = RetrofitUtils.getInstance().getRetrofit().create(IBaseApi.class);
        user = SharedPreferencesHelp.getInstance(this).getUser();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getAvatar())) {
                Glide.with(this).load(user.getAvatar()).into(ivHead);
            } else {
                Glide.with(this).load(R.mipmap.head_icon).into(ivHead);
            }
            ivName.setText(user.getAccount());
            ivMobile.setText(user.getMobile());
        }
        tvVersion.setText(VersionUtils.getVersionName(this));
    }


    @OnClick(R.id.rl_password)
    public void toPassword() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
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

    private Uri mPhotoImageUri;
    private Uri mPickImageUri;
    private Uri mCropImageUri;

    @OnClick(R.id.rl_head)
    public void choseHead() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480, output_Y = 480;
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAMERA) {
//                 String fileName = outputImage.getAbsolutePath() ;
                mCropImageUri = getCropUri("crop");
                cropImageUri(this, mPhotoImageUri, mCropImageUri, 1, 1, output_X, output_Y, REQUEST_CODE_CROP);
//                 uploadFile(fileName);
            } else if (requestCode == REQUEST_CODE_PICK) {
                mCropImageUri = getCropUri("crop");
//                Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                cropImageUri(this, data.getData(), mCropImageUri, 1, 1, output_X, output_Y, REQUEST_CODE_CROP);
//                String pickImagePath = getRealPathFromUri(this ,data.getData());
//                uploadFile(pickImagePath);
            } else if (requestCode == REQUEST_CODE_CROP) {
//                String cropImagePath = getRealPathFromUri(this ,data.getData());
                String photoPath = getRealPathFromUri(this, mCropImageUri);
                uploadFile(photoPath);
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
                    Glide.with(SettingActivity.this).load(urls.get(0)).into(ivHead);
                    showToast("修改头像成功");
                } else {
                    showToast(stringApiResult.getMessage());
                }
            }
        });
    }


    /**
     * 根据图片的Uri获取图片的绝对路径。@uri 图片的uri
     *
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    public static String getRealPathFromUri(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return getRealPathFromUri_Byfile(context, uri);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getRealPathFromUri_Api11To18(context, uri);
        }
        return getRealPathFromUri_AboveApi19(context, uri);//没用到
    }


    //针对图片URI格式为Uri:: file:///storage/emulated/0/DCIM/Camera/IMG_20170613_132837.jpg
    private static String getRealPathFromUri_Byfile(Context context, Uri uri) {
        String uri2Str = uri.toString();
        String filePath = uri2Str.substring(uri2Str.indexOf(":") + 3);
        return filePath;
    }

    /**
     * 适配api19以上,根据uri获取图片的绝对路径
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUri_AboveApi19(Context context, Uri uri) {
        String filePath = null;
        String wholeID = null;

        wholeID = DocumentsContract.getDocumentId(uri);

        // 使用':'分割
        String id = wholeID.split(":")[1];

        String[] projection = {MediaStore.Images.Media.DATA};
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = {id};

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                selection, selectionArgs, null);
        int columnIndex = cursor.getColumnIndex(projection[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

    /**
     * //适配api11-api18,根据uri获取图片的绝对路径。
     * 针对图片URI格式为Uri:: content://media/external/images/media/1028
     */
    private static String getRealPathFromUri_Api11To18(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};

        CursorLoader loader = new CursorLoader(context, uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor != null && cursor.getCount() >= 1) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));
            cursor.close();
        }
        return filePath;
    }
}




