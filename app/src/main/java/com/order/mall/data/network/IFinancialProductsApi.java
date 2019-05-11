package com.order.mall.data.network;

import com.order.mall.data.network.financial.FinancialProduct;
import com.order.mall.data.network.financial.PreyPay;
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

}
