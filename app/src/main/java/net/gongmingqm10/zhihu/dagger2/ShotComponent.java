package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.view.activity.ShotActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ZhihuAppComponent.class,
        modules = ShotModule.class
)
public interface ShotComponent {
    void inject(ShotActivity shotActivity);
}
