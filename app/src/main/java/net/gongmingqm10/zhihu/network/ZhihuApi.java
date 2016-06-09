package net.gongmingqm10.zhihu.network;

import net.gongmingqm10.zhihu.model.Comment;
import net.gongmingqm10.zhihu.model.Shot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ZhihuApi {

    @GET("/v1/shots?per_page=30")
    Call<List<Shot>> listShots(@Query("page") int page, @Query("sort") String sort);

    @GET("/v1/shots/{id}")
    Call<Shot> queryShot(@Path("id") int shotId);

    @GET("/v1/shots/{id}/comments?per_page=20")
    Call<List<Comment>> listShotComments(@Path("id") int shotId);

    @GET("/v1/users/{id}/shots?per_page=30")
    Call<List<Shot>> listUserShots(@Path("id") int userId, @Query("page") int page);
}
