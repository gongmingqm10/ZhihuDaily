package net.gongmingqm10.zhihu;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import net.gongmingqm10.zhihu.data.SharedPreferenceMgr;
import net.gongmingqm10.zhihu.network.NetworkMgr;
import net.gongmingqm10.zhihu.network.ZhihuApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ZhihuAppModule {

    private final Application application;

    public ZhihuAppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    Application application() {
        return application;
    }

    @Provides @Singleton
    Context context() {
        return application.getApplicationContext();
    }

    @Provides @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides @Singleton
    SharedPreferenceMgr sharedPreferenceMgr(Context context) {
        return new SharedPreferenceMgr(context);
    }

    @Provides @Singleton
    NetworkMgr provideNetworkMgr() {
        return new NetworkMgr();
    }

}
