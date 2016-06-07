package net.gongmingqm10.zhihu.network;

import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.network.data.ThemeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZhihuApi {

    @GET("/latest-news")
    Call<Object> getLatestNews();

    @GET("/themes")
    Call<ThemeResponse> getThemes();

    @GET("/v1/shots")
    Call<List<Shot>> listShots(@Query("page") int page, @Query("per_page") int limit);

}
