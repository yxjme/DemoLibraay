package com.jewelermobile.gangfu.zdydemo1.api;

import com.jewelermobile.gangfu.zdydemo1.activity.LinkedBlockingQueueActivity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiInterface {

    /**
     * 获取服务器时间
     *
     * @return
     */
    @POST(Api.serverTime)
    Observable<LinkedBlockingQueueActivity.BaseData<LinkedBlockingQueueActivity.ServerTimeResponse>> serverTime(@Body RequestBody requestBody);

}
