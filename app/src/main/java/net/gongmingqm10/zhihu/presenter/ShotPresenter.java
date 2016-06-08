package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Comment;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.network.ApiCallback;
import net.gongmingqm10.zhihu.network.ZhihuApi;

import java.util.List;

public class ShotPresenter extends Presenter<ShotPresenter.ShotView> {

    public ShotPresenter(ShotView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public void loadShot(int shotId) {
        view.loading(R.string.is_loading);

        zhihuApi.queryShot(shotId).enqueue(new ApiCallback<Shot>() {
            @Override
            public void onSuccess(Shot data) {
                view.loaded();
                view.showShot(data);

                loadShotComments(data.getId());
            }

            @Override
            public void onFailed(String message) {
                view.loaded();
                view.showToast(message);
            }
        });
    }

    private void loadShotComments(int shotId) {
        zhihuApi.listShotComments(shotId).enqueue(new ApiCallback<List<Comment>>() {

            @Override
            public void onSuccess(List<Comment> data) {
                view.showComments(data);
            }

            @Override
            public void onFailed(String message) {
                view.showToast(message);
            }
        });
    }

    public interface ShotView extends BaseView {
        void showShot(Shot shot);
        void showComments(List<Comment> comments);
    }
}
