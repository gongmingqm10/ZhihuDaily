package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.network.ZhihuApi;

public class HomePresenter extends Presenter<HomePresenter.HomeView> {

    public HomePresenter(HomeView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public void loadThemes() {

    }

    public interface HomeView extends BaseView {

    }
}
