package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.model.Theme;
import net.gongmingqm10.zhihu.network.ApiCallback;
import net.gongmingqm10.zhihu.network.ZhihuApi;
import net.gongmingqm10.zhihu.network.data.ThemeResponse;

import java.util.List;

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
        mainView.loading();
        zhihuApi.getThemes().enqueue(new ApiCallback<ThemeResponse>() {
            @Override
            public void onSuccess(ThemeResponse data) {
                mainView.updateList(data.getOthers());
                mainView.loaded();
            }

            @Override
            public void onFailed(String message) {
                mainView.showToast(message);
                mainView.loaded();
            }
        });
    }

    public interface MainView extends BaseView {
        void updateList(List<Theme> themes);
    }
}
