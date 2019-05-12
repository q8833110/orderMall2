package com.order.mall.data.network;

import com.order.mall.model.netword.ApiResult;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

public interface IBaseApi {

    // 图片上传
    @POST("basic/uploadImg")
    Observable<ApiResult<String>> upload(@Body RequestBody body);
}
