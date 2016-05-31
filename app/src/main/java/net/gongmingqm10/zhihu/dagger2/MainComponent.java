package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.view.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ZhihuAppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
