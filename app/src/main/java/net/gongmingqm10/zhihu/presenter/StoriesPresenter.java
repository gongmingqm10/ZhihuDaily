package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.network.ZhihuApi;

public class StoriesPresenter extends Presenter<StoriesPresenter.StoriesView> {

    public StoriesPresenter(StoriesView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public interface StoriesView extends BaseView {

    }
}
