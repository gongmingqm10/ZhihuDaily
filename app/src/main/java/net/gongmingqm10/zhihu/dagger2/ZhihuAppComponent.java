package net.gongmingqm10.zhihu.dagger2;

import android.app.Application;
import android.location.LocationManager;

import net.gongmingqm10.zhihu.ZhihuApp;
import net.gongmingqm10.zhihu.data.SharedPreferenceMgr;
import net.gongmingqm10.zhihu.network.ApiModule;
import net.gongmingqm10.zhihu.network.ZhihuApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ZhihuAppModule.class, ApiModule.class})
public interface ZhihuAppComponent {
    // Field injection for any dependencies of the ZhihuApp
    void inject(ZhihuApp application);

    // Exported for child-components: Such as ActivityComponent
    Application application();
//
    SharedPreferenceMgr sharedPreferenceMgr();

    LocationManager locationManager();

    ZhihuApi getZhihuApi();
}
