package com.order.mall.data.network;

import com.order.mall.data.network.login.UserRespDTO;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 登陆
 */
public interface ILoginApi {

    @POST("basic/getVerifyCode")
    Observable<ApiResult<String>> getVerifyCode(@Query("type") int type ,@Query("mobile") String mobile);

    @POST("user/loginWithMobile")
    Observable<ApiResult<UserRespDTO>> mobileLogin(@Query("mobile") String mobile  , @Query("verifyCode") String verifyCode);

    @POST("user/loginWithAccount")
    Observable<ApiResult<UserRespDTO>> Accountlogin(@Query("account") String account  , @Query("password") String password);

    @POST("user/register")
    Observable<ApiResult<Boolean>> register(@Query("account") String  account ,
                                        @Query("mobile") String mobile ,
                                        @Query("verifyCode") String verifyCode ,
                                        @Query("password") String password ,
                                        @Query("parentId") String parentId );
}
