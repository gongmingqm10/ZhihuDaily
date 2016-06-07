package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.network.ZhihuApi;
import net.gongmingqm10.zhihu.presenter.DesignersPresenter;
import net.gongmingqm10.zhihu.presenter.HomePresenter;
import net.gongmingqm10.zhihu.presenter.ShotsPresenter;
import net.gongmingqm10.zhihu.presenter.StoriesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private HomePresenter.HomeView mainView;
    private DesignersPresenter.DesignersView designersView;
    private ShotsPresenter.ShotsView shotsView;
    private StoriesPresenter.StoriesView storiesView;

    public MainModule(HomePresenter.HomeView mainView) {
        this.mainView = mainView;
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
    public HomePresenter.HomeView provideMainView() {
        return mainView;
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
    public HomePresenter provideMainPresenter(HomePresenter.HomeView mainView, ZhihuApi zhihuApi) {
        return new HomePresenter(mainView, zhihuApi);
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
