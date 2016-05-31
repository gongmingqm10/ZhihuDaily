package net.gongmingqm10.zhihu.presenter;

public interface BaseView {
    void loading();
    void loading(String message);
    void loaded();
    void showToast(String message);
    void showToast(int resId);
}
