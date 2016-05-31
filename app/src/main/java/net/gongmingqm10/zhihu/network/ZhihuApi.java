package net.gongmingqm10.zhihu.network;

import net.gongmingqm10.zhihu.network.data.ThemeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZhihuApi {

    @GET("/latest-news")
    Call<Object> getLatestNews();

    @GET("/themes")
    Call<ThemeResponse> getThemes();


}
