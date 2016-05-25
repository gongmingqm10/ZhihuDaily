package net.gongmingqm10.zhihu.view.activity;

import net.gongmingqm10.zhihu.ZhihuAppComponent;
import net.gongmingqm10.zhihu.view.fragment.MainFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ZhihuAppComponent.class, modules = ActivityModule.class)
public interface MainComponent extends BaseComponent {
    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
}

