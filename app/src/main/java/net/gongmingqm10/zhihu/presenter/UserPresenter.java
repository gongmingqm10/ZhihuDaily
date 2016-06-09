package net.gongmingqm10.zhihu.presenter;

import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.network.ApiCallback;
import net.gongmingqm10.zhihu.network.ZhihuApi;

import java.util.List;

public class UserPresenter extends Presenter<UserPresenter.UserView> {

    public UserPresenter(UserView view, ZhihuApi zhihuApi) {
        super(view, zhihuApi);
    }

    public void listUserShots(int userId, int pageNumber) {
        zhihuApi.listUserShots(userId, pageNumber).enqueue(new ApiCallback<List<Shot>>() {
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

    public interface UserView extends BaseView {
        void refreshList(List<Shot> data);
    }
}
