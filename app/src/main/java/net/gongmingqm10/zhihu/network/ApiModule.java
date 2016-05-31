package net.gongmingqm10.zhihu.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ZhihuApi provideZhihuApi() {
        return RetrofitAdapter.getInstance();
    }

}
