package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.view.activity.MainActivity;
import net.gongmingqm10.zhihu.view.activity.ShotActivity;
import net.gongmingqm10.zhihu.view.fragment.DesignersFragment;
import net.gongmingqm10.zhihu.view.fragment.ShotsFragment;
import net.gongmingqm10.zhihu.view.fragment.StoriesFragment;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ZhihuAppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity mainActivity);

    void inject(ShotsFragment shotsFragment);

    void inject(DesignersFragment designersFragment);

    void inject(StoriesFragment storiesFragment);
}
