package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.network.ZhihuApi;
import net.gongmingqm10.zhihu.presenter.ShotPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ShotModule {

    private ShotPresenter.ShotView shotView;

    public ShotModule(ShotPresenter.ShotView shotView) {
        this.shotView = shotView;
    }

    @Provides
    public ShotPresenter.ShotView provideShotView() {
        return shotView;
    }

    @Provides
    public ShotPresenter provideShotPresenter(ShotPresenter.ShotView shotView, ZhihuApi zhihuApi) {
        return new ShotPresenter(shotView, zhihuApi);
    }
}
