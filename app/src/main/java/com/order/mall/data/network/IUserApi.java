package com.order.mall.data.network;

import com.order.mall.data.network.user.Address;
import com.order.mall.data.network.user.AlipayList;
import com.order.mall.data.network.user.BankList;
import com.order.mall.data.network.user.AppUrlInfo;
import com.order.mall.data.network.user.BonusScoreDetails;
import com.order.mall.data.network.user.BounsScoreList;
import com.order.mall.data.network.user.CashList;
import com.order.mall.data.network.user.CashScoreList;
import com.order.mall.data.network.user.CashScoreDetails;
import com.order.mall.data.network.user.CashSuccess;
import com.order.mall.data.network.user.ConsumeDetails;
import com.order.mall.data.network.user.ConsumeList;
import com.order.mall.data.network.user.RechargeCenter;
import com.order.mall.data.network.user.RechargeDetails;
import com.order.mall.data.network.user.RechargeSuccess;
import com.order.mall.data.network.user.ShoppingList;
import com.order.mall.data.network.user.TradeBalanceList;
import com.order.mall.data.network.user.TradeDetails;
import com.order.mall.data.network.user.TradeOrderList;
import com.order.mall.data.network.user.UserData;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.data.network.user.UserTeam;
import com.order.mall.data.network.user.WeixinList;
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
    Observable<ApiResult<Boolean>> mergeAddress(@FieldMap Map<String, Object> map);


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
    Observable<ApiResult<UserTeam>> userTeamInfo(@Query("userId") long userId);

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
    Observable<ApiResult> changePwd(@Query("userId") long userId,
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

    //提现列表
    @POST("user/getAppUrlInfo")
    Observable<ApiResult<AppUrlInfo>> getAppUrlInfo(@Query("type") String type);

    //现金积分转报单积分
    @POST("balanceDetails/cashIntoTrade")
    Observable<ApiResult> cashIntoTrade(@Query("value") String value,
                                        @Query("userId") long userId);

    //奖金积分转现金积分
    @POST("balanceDetails/bonusIntoCash")
    Observable<ApiResult> bonusIntoCash(@Query("value") String value,
                                        @Query("userId") long userId);

    //单个奖金积分明细
    @POST("balanceDetails/oneBonusBalanceDetails")
    Observable<ApiResult<BonusScoreDetails>> oneBonusBalanceDetails(@Query("id") String id);

    //消费积分列表（全部）
    @POST("balanceDetails/consumeBalanceDetailsList")
    Observable<ApiResult<ConsumeList>> consumeBalanceDetailsListAll(@Query("pageNum") int pageNum,
                                                                    @Query("pageSize") int pageSize,
                                                                    @Query("userId") long userId);

    //消费积分列表
    @POST("balanceDetails/consumeBalanceDetailsList")
    Observable<ApiResult<ConsumeList>> consumeBalanceDetailsList(@Query("pageNum") int pageNum,
                                                                 @Query("pageSize") int pageSize,
                                                                 @Query("userId") long userId,
                                                                 @Query("type") int type);

    //单个消费积分明细
    @POST("balanceDetails/oneConsumeBalanceDetails")
    Observable<ApiResult<ConsumeDetails>> oneConsumeBalanceDetails(@Query("id") String id);

    //获取RMB与报单积分汇率
    @POST("user/getExchangeRate")
    Observable<ApiResult> getExchangeRate();


    //充值报单积分
    @POST("user/topUp")
    Observable<ApiResult<RechargeSuccess>> topUp(@Query("userId") long userId,
                                                 @Query("amount") int amount,
                                                 @Query("payWay") int payWay);

    //充值去人
    @POST("user/topUpComfirm")
    Observable<ApiResult<String>> topConfirm(@Query("id") String id,
                                                 @Query("proofOfPay") String proofOfPay
                                                );

    //取消充值报单积分
    @POST("user/topUpCancle")
    Observable<ApiResult> topUpCancle(@Query("id") String id);

    //获取现金积分与RMB汇率
    @POST("userEncashment/encashValueRate")
    Observable<ApiResult> encashValueRate();

    //获取支付宝列表
    @POST("userPaymentWay/userPayWayAliList")
    Observable<ApiResult<List<AlipayList>>> userPayWayAliList(@Query("userId") long userId);

    //获取银行卡列表
    @POST("userPaymentWay/userPayWayBankList")
    Observable<ApiResult<List<BankList>>> userPayWayBankList(@Query("userId") long userId);

    //获取微信列表
    @POST("userPaymentWay/userPayWayWeixinList")
    Observable<ApiResult<List<WeixinList>>> userPayWayWeixinList(@Query("userId") long userId);

    //提现
    @POST("userEncashment/userEncashment")
    Observable<ApiResult<CashSuccess>> userEncashment(@Query("userId") long userId,
                                                      @Query("encashMoney") double encashMoney,
                                                      @Query("reciveWay") int reciveWay,
                                                      @Query("accountNo") String accountNo,
                                                      @Query("accountName") String accountName);

    //单个提现详情
    @POST("userEncashment/oneUserEncashment")
    Observable<ApiResult<CashSuccess>> oneUserEncashment(@Query("id") String id);


    //新增或保存支付宝
    @POST("userPaymentWay/mergeUserPayWayAli")
    Observable<ApiResult> mergeUserPayWayAli(@Query("userId") long userId,
                                             @Query("aliPayAccount") String aliPayAccount,
                                             @Query("accountRealName") String accountRealName,
                                             @Query("openOrNot") boolean openOrNot);

    //保存支付宝
    @POST("userPaymentWay/mergeUserPayWayAli")
    Observable<ApiResult> mergeUserPayWayAli2(@Query("userId") long userId,
                                              @Query("aliPayAccount") String aliPayAccount,
                                              @Query("accountRealName") String accountRealName,
                                              @Query("openOrNot") boolean openOrNot,
                                              @Query("id") String id);


    //新增微信
    @POST("userPaymentWay/mergeUserPayWayWeixin")
    Observable<ApiResult> mergeUserPayWayWeixin(@Query("userId") long userId,
                                                @Query("weixinPayAccount") String aliPayAccount,
                                                @Query("accountRealName") String accountRealName,
                                                @Query("openOrNot") boolean openOrNot);

    //修改微信
    @POST("userPaymentWay/mergeUserPayWayWeixin")
    Observable<ApiResult> mergeUserPayWayWeixin2(@Query("userId") long userId,
                                                 @Query("weixinPayAccount") String aliPayAccount,
                                                 @Query("accountRealName") String accountRealName,
                                                 @Query("openOrNot") boolean openOrNot,
                                                 @Query("id") String id);


    //新增微信
    @POST("userPaymentWay/mergeUserPayWayBank")
    Observable<ApiResult> mergeUserPayWayBank(@Query("userId") long userId,
                                              @Query("accountName") String accountName,
                                              @Query("bankName") String bankName,
                                              @Query("subbranchName") String subbranchName,
                                              @Query("bankNo") String bankNo,
                                              @Query("openOrNot") boolean openOrNot);

    //修改微信
    @POST("userPaymentWay/mergeUserPayWayBank")
    Observable<ApiResult> mergeUserPayWayBank2(@Query("userId") long userId,
                                               @Query("accountName") String accountName,
                                               @Query("bankName") String bankName,
                                               @Query("subbranchName") String subbranchName,
                                               @Query("bankNo") String bankNo,
                                               @Query("openOrNot") boolean openOrNot,
                                               @Query("id") String id); //修改微信

    @POST("shoppingOrder/receive")
    Observable<ApiResult> receive(@Query("id") String id);

    /**
     * 删除地址
     * @param deliveryId
     * @param userId
     * @return
     */
    @POST("userDelivery/delUserDeliveryById")
    Observable<ApiResult<Boolean>> delUserDelivery(@Query("deliveryId") String deliveryId ,
                                          @Query("userId") int userId);


}
