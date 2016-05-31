package net.gongmingqm10.zhihu;

import android.app.Application;
import android.location.LocationManager;

import net.gongmingqm10.zhihu.dagger2.DaggerZhihuAppComponent;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppModule;
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
        setupComponent();

        locationManager.getAllProviders();
        sharedPreferenceMgr.toString();
    }

    private void setupComponent() {
        zhihuAppComponent = DaggerZhihuAppComponent.builder()
                .zhihuAppModule(new ZhihuAppModule(this))
                .build();
        zhihuAppComponent.inject(this);
    }

    public ZhihuAppComponent component() {
        return zhihuAppComponent;
    }
}
