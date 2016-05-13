package net.gongmingqm10.zhihu.network;

import net.gongmingqm10.zhihu.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkMgr {

    private static final String BASE_URL = "http://gongmingqm10.net";

    private static NetworkMgr instance;
    private final Retrofit retrofit;

    private NetworkMgr() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient()).build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(loggingInterceptor);

        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request().newBuilder()
                        .addHeader("App-Version", BuildConfig.VERSION_NAME);
                // Add any headers you want here.
                return chain.proceed(requestBuilder.build());
            }
        });
        return httpClient;
    }

    public static NetworkMgr getInstance() {
        if (instance == null) {
            instance = new NetworkMgr();
        }
        return instance;
    }

    public static ZhihuApi getZhihuApi() {
        return getInstance().retrofit.create(ZhihuApi.class);
    }

}
