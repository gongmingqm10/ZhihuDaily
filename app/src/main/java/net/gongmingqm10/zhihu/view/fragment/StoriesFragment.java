package net.gongmingqm10.zhihu.view.fragment;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerMainComponent;
import net.gongmingqm10.zhihu.dagger2.MainModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.presenter.StoriesPresenter;

public class StoriesFragment extends MainBaseFragment implements StoriesPresenter.StoriesView {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_stories;
    }

    @Override
    protected void setupComponent(ZhihuAppComponent appComponent) {
        DaggerMainComponent.builder().zhihuAppComponent(appComponent)
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    protected int getTitleResId() {
        return R.string.stories;
    }
}
