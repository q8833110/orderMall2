package com.order.mall.data.network;

import com.order.mall.data.network.user.Address;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.model.netword.ApiResult;

import java.util.List;

import retrofit2.http.POST;
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

    @POST("userDelivery/mergeUserDelivery")
    Observable<ApiResult<String>> mergeAddress(@Query("id") String  id ,
                                                @Query("userId") long userId ,
                                                @Query("reciver") String reciver  ,
                                                @Query("mobile") String mobile  ,
                                               @Query("address") String address  ,
                                               @Query("addressDetail") String addressDetail  ,
                                               @Query("isDefault") boolean isDefault
                                               );

    @POST("userDelivery/oneUserDeliverAddress")
    Observable<ApiResult<Address>> oneUserAddress(@Query("id") String  id);
}
