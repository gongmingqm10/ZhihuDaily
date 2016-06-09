package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.view.activity.UserActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ZhihuAppComponent.class,
        modules = UserModule.class
)
public interface UserComponent {
    void inject(UserActivity UserActivity);
}
