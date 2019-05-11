package com.order.mall.data.network;

import com.order.mall.data.network.financial.FinancialProduct;
import com.order.mall.data.network.financial.FinancialProductOrder;
import com.order.mall.data.network.financial.PreyPay;
import com.order.mall.data.network.financial.SellOrder;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 抢单商城
 */
public interface IFinancialProductsApi {

    @POST("financialProducts/listFinancialProducts")
    Observable<ApiResult<FinancialProduct>> listfinancialProducts(@Query("pageNum") int  pageNum , @Query("pageSize") int  pageSize);

    @POST("financialProducts/preparePay")
    Observable<ApiResult<PreyPay>> prepay(@Query("productsId") int  productsId , @Query("userId") int  userId);

    @POST("financialProductsOrder/createFinancialProductsOrder")
    Observable<ApiResult<FinancialProductOrder>> pay(@Query("productsId") int  productsId , @Query("userId") int  userId);


    // 卖出商品列表
    @POST("financialProductsOrder/listFinancialProductsOrder")
    Observable<ApiResult<SellOrder>> sellList(@Query("pageNum") int  pageNum , @Query("pageSize") int  pageSize
                                         , @Query("userId") int  userId , @Query("type") int  type);

    // 卖出商品详情
    @POST("financialProductsOrder/financialProductsOrderDetail")
    Observable<ApiResult<SellOrder.DataBean>> sellOrderDetail(@Query("id") int  id );


    // 卖出商品订单
    @POST("financialProductsOrder/financialProductsOrderSell")
    Observable<ApiResult<Boolean>> sell(@Query("id") int  id );
}
