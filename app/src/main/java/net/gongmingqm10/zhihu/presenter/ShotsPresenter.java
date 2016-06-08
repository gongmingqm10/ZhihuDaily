package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.network.ApiCallback;
import net.gongmingqm10.zhihu.network.ZhihuApi;

import java.util.List;

public class ShotsPresenter extends Presenter<ShotsPresenter.ShotsView> {

    public ShotsPresenter(ShotsView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public void loadShots(int pageNumber, String sort) {
        zhihuApi.listShots(pageNumber, sort).enqueue(new ApiCallback<List<Shot>>() {
            @Override
            public void onSuccess(List<Shot> data) {
                view.refreshList(data);
            }

            @Override
            public void onFailed(String message) {
                view.showToast(message);
            }
        });
    }

    public interface ShotsView extends BaseView {
        void refreshList(List<Shot> data);
    }
}
