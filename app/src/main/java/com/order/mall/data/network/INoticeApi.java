package com.order.mall.data.network;

import com.order.mall.data.network.homepage.Notice;
import com.order.mall.model.netword.ApiResult;

import retrofit2.http.GET;
import rx.Observable;

public interface INoticeApi {

    @GET("/notice")
    Observable<ApiResult<Notice>> notice();
}
