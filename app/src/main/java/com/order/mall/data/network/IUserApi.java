package com.order.mall.data.network;

import com.order.mall.data.network.user.Address;
import com.order.mall.data.network.user.BounsScoreList;
import com.order.mall.data.network.user.CashList;
import com.order.mall.data.network.user.CashScoreList;
import com.order.mall.data.network.user.CashScoreDetails;
import com.order.mall.data.network.user.RechargeCenter;
import com.order.mall.data.network.user.RechargeDetails;
import com.order.mall.data.network.user.ShoppingList;
import com.order.mall.data.network.user.TradeBalanceList;
import com.order.mall.data.network.user.TradeDetails;
import com.order.mall.data.network.user.TradeOrderList;
import com.order.mall.data.network.user.UserData;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.data.network.user.UserTeam;
import com.order.mall.model.netword.ApiResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IUserApi {
    @POST("userDelivery/userDeliverAddress")
    Observable<ApiResult<List<UserDeliverAddress>>> getUserAddress(@Query("userId") long userId);

    /**
     * 删除地址
     *
     * @param deliveryId
     * @param userId
     * @return
     */
    @POST("userDelivery/delUserDeliveryById")
    Observable<ApiResult<String>> deleteAddress(@Query("deliveryId") long deliveryId, @Query("userId") long userId);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("userDelivery/mergeUserDelivery")
    Observable<ApiResult<String>> mergeAddress(@FieldMap Map<String, Object> map);


    @POST("userDelivery/oneUserDeliverAddress")
    Observable<ApiResult<Address>> oneUserAddress(@Query("id") String id);

    //个人中心获取用户信息
    @POST("user/personalCenter")
    Observable<ApiResult<UserData>> getUserData(@Query("userId") long id);

    //报单积分列表
    @POST("balanceDetails/tradeBalanceDetailsList")
    Observable<ApiResult<TradeBalanceList>> getTradeBalanceDetailsList(@Query("pageNum") int pageNum,
                                                                       @Query("pageSize") int pageSize,
                                                                       @Query("userId") long userId,
                                                                       @Query("type") int type);

    //报单积分列表(全部)
    @POST("balanceDetails/tradeBalanceDetailsList")
    Observable<ApiResult<TradeBalanceList>> getAllTradeBalanceDetailsList(@Query("pageNum") int pageNum,
                                                                          @Query("pageSize") int pageSize,
                                                                          @Query("userId") long userId);

    //报单积分详情
    @POST("balanceDetails/oneTradeBalanceDetails")
    Observable<ApiResult<TradeDetails>> oneTradeBalanceDetails(@Query("id") String id);

    //充值中心
    @POST("user/listTopUpOrder")
    Observable<ApiResult<RechargeCenter>> getRechargeList(@Query("pageNum") int pageNum,
                                                          @Query("pageSize") int pageSize,
                                                          @Query("userId") long userId,
                                                          @Query("type") int type);

    //我的团队
    @POST("user/userTeamInfo")
    Observable<ApiResult<UserTeam>> userTeamInfo(@Query("userId") int userId);

    //我的交易订单列表
    @POST("financialProductsOrder/listFinancialProductsOrder")
    Observable<ApiResult<TradeOrderList>> getTradeList(@Query("pageNum") int pageNum,
                                                       @Query("pageSize") int pageSize,
                                                       @Query("userId") long userId,
                                                       @Query("type") int type);

    //我的交易订单列表
    @POST("shoppingOrder/listShoppingOrder")
    Observable<ApiResult<ShoppingList>> getShoppingList(@Query("pageNum") int pageNum,
                                                        @Query("pageSize") int pageSize,
                                                        @Query("userId") long userId,
                                                        @Query("type") int type);

    //我的交易订单列表
    @POST("user/modifyPassword")
    Observable<ApiResult> changePwd(@Query("userId") int userId,
                                    @Query("oldPassword") String oldPassword,
                                    @Query("newPassword") String newPassword,
                                    @Query("newPasswordConfirm") String newPasswordConfirm);

    //充值订单详情
    @POST("user/topUpOrderDetail")
    Observable<ApiResult<RechargeDetails>> rechargeDetails(@Query("orderId") String orderId);


    //现金积分列表
    @POST("balanceDetails/cashBalanceDetailsList")
    Observable<ApiResult<CashScoreList>> cashBalanceDetailsList(@Query("pageNum") int pageNum,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("userId") long userId,
                                                                @Query("type") int type);

    //全部现金积分列表
    @POST("balanceDetails/cashBalanceDetailsList")
    Observable<ApiResult<CashScoreList>> cashBalanceDetailsListAll(@Query("pageNum") int pageNum,
                                                                   @Query("pageSize") int pageSize,
                                                                   @Query("userId") long userId);

    //充值订单详情
    @POST("balanceDetails/oneCashBalanceDetails")
    Observable<ApiResult<CashScoreDetails>> oneCashBalanceDetails(@Query("id") String id);


    //奖金积分列表
    @POST("balanceDetails/bonusBalanceDetailsList")
    Observable<ApiResult<BounsScoreList>> bonusBalanceDetailsList(@Query("pageNum") int pageNum,
                                                                  @Query("pageSize") int pageSize,
                                                                  @Query("userId") long userId,
                                                                  @Query("type") int type);

    //奖金积分列表（全部）
    @POST("balanceDetails/bonusBalanceDetailsList")
    Observable<ApiResult<BounsScoreList>> bonusBalanceDetailsListAll(@Query("pageNum") int pageNum,
                                                                     @Query("pageSize") int pageSize,
                                                                     @Query("userId") long userId);

    //提现列表（全部）
    @POST("userEncashment/userEncashmentList")
    Observable<ApiResult<CashList>> userEncashmentListAll(@Query("pageNum") int pageNum,
                                                          @Query("pageSize") int pageSize,
                                                          @Query("userId") long userId);

    //提现列表
    @POST("userEncashment/userEncashmentList")
    Observable<ApiResult<CashList>> userEncashmentList(@Query("pageNum") int pageNum,
                                                       @Query("pageSize") int pageSize,
                                                       @Query("userId") long userId,
                                                       @Query("encashStatus") int encashStatus);
}
