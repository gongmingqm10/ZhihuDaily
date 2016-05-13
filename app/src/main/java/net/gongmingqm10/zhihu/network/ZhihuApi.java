package net.gongmingqm10.zhihu.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZhihuApi {

    @GET("/latest-news")
    Call<Object> getLatestNews();

}
