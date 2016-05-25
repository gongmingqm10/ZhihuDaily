package net.gongmingqm10.zhihu;

import android.app.Application;
import android.location.LocationManager;

import net.gongmingqm10.zhihu.data.SharedPreferenceMgr;

import javax.inject.Inject;

public class ZhihuApp extends Application {

    private ZhihuAppComponent zhihuAppComponent;

    @Inject
    LocationManager locationManager;

    @Inject
    SharedPreferenceMgr sharedPreferenceMgr;

    @Override
    public void onCreate() {
        super.onCreate();
        zhihuAppComponent = DaggerZhihuAppComponent.builder().zhihuAppModule(new ZhihuAppModule(this)).build();
        zhihuAppComponent.inject(this);

        locationManager.getAllProviders();
        sharedPreferenceMgr.toString();
    }

    public ZhihuAppComponent component() {
        return zhihuAppComponent;
    }
}
