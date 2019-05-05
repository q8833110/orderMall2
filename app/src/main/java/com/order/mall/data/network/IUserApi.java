package com.order.mall.data.network;

import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.model.netword.ApiResult;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IUserApi {
    @POST("userDelivery/userDeliverAddress")
    Observable<ApiResult<List<UserDeliverAddress>>> getUserAddress(@Query("userId") long userId );

}
