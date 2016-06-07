package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.network.ZhihuApi;

public class ShotsPresenter extends Presenter<ShotsPresenter.ShotsView> {

    public ShotsPresenter(ShotsView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public interface ShotsView extends BaseView {

    }
}
