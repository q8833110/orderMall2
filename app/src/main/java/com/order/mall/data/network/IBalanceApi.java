package com.order.mall.data.network;

import com.order.mall.data.network.user.Bounds;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IBalanceApi {

    @POST("balanceDetails/bonusBalanceDetailsList")
    Observable<ApiResult<Bounds>> getVerifyCode(@Query("pageNum") int pageNum , @Query("pageSize") int  pageSize
                                ,@Query("userId") long  userId , @Query("type") int  type  );

    @POST("balanceDetails/bonusIntoCash")
    Observable<ApiResult<Boolean>> BonusIntoCash(@Query("userId") String  userId , @Query("value") String  value);
}
