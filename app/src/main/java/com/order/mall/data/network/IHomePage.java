package com.order.mall.data.network;

import com.order.mall.data.network.homepage.Home;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.POST;
import rx.Observable;

public interface IHomePage {

    @POST("home/homePageDetails")
    Observable<ApiResult<Home>> homePage();
}
