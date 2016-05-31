package net.gongmingqm10.zhihu.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private static final String BASE_URL = "https://api.dribbble.com";
    private static final String ACCESS_TOKEN = "34d7a0e44d9e6231eed4291f81e6390a9dd0baf24e9c9b0fea992fe9c24baa4e";

    private static RetrofitAdapter retrofitAdapter;
    private ZhihuApi zhihuApi;

    public static ZhihuApi getInstance() {
        if (retrofitAdapter == null) {
            retrofitAdapter = new RetrofitAdapter();
        }
        return retrofitAdapter.zhihuApi;
    }

    private RetrofitAdapter() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient()).build();
        zhihuApi = retrofit.create(ZhihuApi.class);
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor networkInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
                return chain.proceed(requestBuilder.build());
            }
        };

        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor).build();
    }

}
