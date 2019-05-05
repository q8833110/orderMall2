package com.order.mall.data.network;

import com.order.mall.data.network.login.VerifyCodeReqDTO;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 登陆
 */
public interface ILoginApi {

    @POST("basic/getVerifyCode")
    Observable<ApiResult<String>> getVerifyCode(@Body VerifyCodeReqDTO reqDTO);
}
