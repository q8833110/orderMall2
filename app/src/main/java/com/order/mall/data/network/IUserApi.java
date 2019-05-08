package com.order.mall.data.network;

import com.order.mall.data.network.user.Address;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.model.netword.ApiResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

public interface IUserApi {
    @POST("userDelivery/userDeliverAddress")
    Observable<ApiResult<List<UserDeliverAddress>>> getUserAddress(@Query("userId") long userId );

    /**
     * 删除地址
     * @param deliveryId
     * @param userId
     * @return
     */
    @POST("userDelivery/delUserDeliveryById")
    Observable<ApiResult<String>> deleteAddress(@Query("deliveryId") long deliveryId ,@Query("userId") long userId  );

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("userDelivery/mergeUserDelivery")
    Observable<ApiResult<String>> mergeAddress(@FieldMap Map<String  , Object> map ) ;


    @POST("userDelivery/oneUserDeliverAddress")
    Observable<ApiResult<Address>> oneUserAddress(@Query("id") String  id);
}
