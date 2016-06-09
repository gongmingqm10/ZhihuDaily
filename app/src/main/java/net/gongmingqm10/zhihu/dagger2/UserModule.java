package net.gongmingqm10.zhihu.dagger2;

import net.gongmingqm10.zhihu.network.ZhihuApi;
import net.gongmingqm10.zhihu.presenter.UserPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    private UserPresenter.UserView userView;

    public UserModule(UserPresenter.UserView userView) {
        this.userView = userView;
    }

    @Provides
    public UserPresenter.UserView provideUserView() {
        return userView;
    }

    @Provides
    public UserPresenter provideUserPresenter(UserPresenter.UserView view, ZhihuApi api) {
        return new UserPresenter(view, api);
    }
}
