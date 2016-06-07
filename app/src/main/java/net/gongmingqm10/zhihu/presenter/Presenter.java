package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.network.ZhihuApi;

public abstract class Presenter<T extends BaseView> {

    protected ZhihuApi zhihuApi;
    protected T view;

    public Presenter(T view, ZhihuApi zhihuApi) {
        this.view = view;
        this.zhihuApi = zhihuApi;
    }

    public void start() {
        // Auto Override the start method whenever you need it.
    }

    public void stop() {
        // Auto Override the stop method whenever you need it
    }
}
