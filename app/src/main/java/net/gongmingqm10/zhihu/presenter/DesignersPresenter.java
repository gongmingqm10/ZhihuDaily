package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.network.ZhihuApi;

public class DesignersPresenter extends Presenter<DesignersPresenter.DesignersView> {

    public DesignersPresenter(DesignersView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public interface DesignersView extends BaseView {

    }
}
