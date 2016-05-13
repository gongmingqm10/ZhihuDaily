package net.gongmingqm10.zhihu.presenter;

public abstract class Presenter {
    public void start() {
        // Auto Override the start method whenever you need it.
    }

    public void stop() {
        // Auto Override the stop method whenever you need it
    }

    public abstract void attachView(BaseView view);
}
