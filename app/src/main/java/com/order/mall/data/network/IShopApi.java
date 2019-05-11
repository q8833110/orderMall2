package com.order.mall.data.network;

import com.order.mall.data.network.shop.ShopGoods;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IShopApi {
    @POST("shopGoods/listShopGoods")
    Observable<ApiResult<ShopGoods>> getVerifyCode(@Query("type") int type , @Query("mobile") String mobile);

}
