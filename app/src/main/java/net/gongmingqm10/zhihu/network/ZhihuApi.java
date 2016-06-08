package net.gongmingqm10.zhihu.network;

import net.gongmingqm10.zhihu.model.Shot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZhihuApi {

    @GET("/v1/shots?per_page=30")
    Call<List<Shot>> listShots(@Query("page") int page, @Query("sort") String sort);

}
