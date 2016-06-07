package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.network.ZhihuApi;

public class MainPresenter extends Presenter {

    private MainView mainView;
    private ZhihuApi zhihuApi;

    public MainPresenter(MainView mainView, ZhihuApi zhihuApi) {
        this.mainView = mainView;
        this.zhihuApi = zhihuApi;
    }

    @Override
    public void attachView(BaseView view) {
        mainView = (MainView) view;
    }

    public void loadThemes() {

    }

    public interface MainView extends BaseView {

    }
}
