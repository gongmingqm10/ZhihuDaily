package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.network.ZhihuApi;
import net.gongmingqm10.zhihu.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private MainPresenter.MainView view;

    public MainModule(MainPresenter.MainView view) {
        this.view = view;
    }

    @Provides
    public MainPresenter.MainView provideMainView() {
        return view;
    }

    @Provides
    public MainPresenter provideMainPresenter(MainPresenter.MainView mainView, ZhihuApi zhihuApi) {
        return new MainPresenter(mainView, zhihuApi);
    }
}
