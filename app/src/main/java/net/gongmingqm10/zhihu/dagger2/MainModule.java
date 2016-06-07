package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.network.ZhihuApi;
import net.gongmingqm10.zhihu.presenter.DesignersPresenter;
import net.gongmingqm10.zhihu.presenter.ShotsPresenter;
import net.gongmingqm10.zhihu.presenter.StoriesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private DesignersPresenter.DesignersView designersView;
    private ShotsPresenter.ShotsView shotsView;
    private StoriesPresenter.StoriesView storiesView;

    public MainModule() {
    }

    public MainModule(DesignersPresenter.DesignersView designersView) {
        this.designersView = designersView;
    }

    public MainModule(ShotsPresenter.ShotsView shotsView) {
        this.shotsView = shotsView;
    }

    public MainModule(StoriesPresenter.StoriesView storiesView) {
        this.storiesView = storiesView;
    }

    @Provides
    public StoriesPresenter.StoriesView provideStoriesView() {
        return storiesView;
    }

    @Provides
    public ShotsPresenter.ShotsView provideShotsView() {
        return shotsView;
    }

    @Provides
    public DesignersPresenter.DesignersView provideDesignersView() {
        return designersView;
    }

    @Provides
    public DesignersPresenter provideDesignersPresenter(DesignersPresenter.DesignersView designersView, ZhihuApi zhihuApi) {
        return new DesignersPresenter(designersView, zhihuApi);
    }

    @Provides
    public ShotsPresenter provideShotsPresenter(ShotsPresenter.ShotsView shotsView, ZhihuApi zhihuApi) {
        return new ShotsPresenter(shotsView, zhihuApi);
    }

    @Provides
    public StoriesPresenter provideStoriesPresenter(StoriesPresenter.StoriesView storiesView, ZhihuApi zhihuApi) {
        return new StoriesPresenter(storiesView, zhihuApi);
    }
}
