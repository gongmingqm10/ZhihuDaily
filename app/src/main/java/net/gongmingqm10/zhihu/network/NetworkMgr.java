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

    private ZhihuApi zhihuApi;

    public NetworkMgr() {
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
                        .addHeader("App-Version", BuildConfig.VERSION_NAME);
                // Add any headers you want here.
                return chain.proceed(requestBuilder.build());
            }
        };

        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor).build();
    }

    public ZhihuApi getZhihuApi() {
        return zhihuApi;
    }
}
