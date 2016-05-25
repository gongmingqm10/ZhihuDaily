package net.gongmingqm10.zhihu;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import net.gongmingqm10.zhihu.data.SharedPreferenceMgr;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ZhihuAppModule.class)
public interface ZhihuAppComponent {
    // Field injection for any dependencies of the ZhihuApp
    void inject(ZhihuApp application);

    // Exported for child-components: Such as ActivityComponent
    Application application();

    SharedPreferenceMgr sharedPreferenceMgr();

    LocationManager locationManager();
}
