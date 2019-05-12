package com.order.mall.data.network;

import com.order.mall.data.network.financial.FinancialProductOrder;
import com.order.mall.data.network.financial.PreyPay;
import com.order.mall.data.network.shop.PrePay;
import com.order.mall.data.network.shop.ShopGoods;
import com.order.mall.data.network.shop.ShopOrder;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IShopApi {
    @POST("shopGoods/listShopGoods")
    Observable<ApiResult<ShopGoods>> shopList(@Query("pageNum") int pageName , @Query("pageSize") int pageSize
       ,@Query("name") String name );

    @POST("shopGoods/shopGoodsDetail")
    Observable<ApiResult<ShopGoods.ShopGood>> shopDetails(@Query("id") int id );

    @POST("shopGoods/preparePay")
    Observable<ApiResult<PreyPay>> shopPay(@Query("goodsId") int goodsId ,
                                           @Query("userId") int userId);

    @POST("shoppingOrder/createShoppingOrder")
    Observable<ApiResult<ShopOrder>> pay(@Query("productsId") int  productsId , @Query("userId") int  userId);


}
