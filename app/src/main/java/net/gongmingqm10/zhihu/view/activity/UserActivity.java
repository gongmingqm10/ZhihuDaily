package net.gongmingqm10.zhihu.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerUserComponent;
import net.gongmingqm10.zhihu.dagger2.UserModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.model.User;
import net.gongmingqm10.zhihu.presenter.Presenter;
import net.gongmingqm10.zhihu.presenter.UserPresenter;
import net.gongmingqm10.zhihu.view.adapter.ShotsRecyclerAdapter;
import net.gongmingqm10.zhihu.view.util.Constants;
import net.gongmingqm10.zhihu.view.util.ImageLoader;
import net.gongmingqm10.zhihu.view.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends BaseActivity implements UserPresenter.UserView {

    @BindView(R.id.user_info_header)
    RecyclerViewHeader userInfoHeader;

    @BindView(R.id.user_shots_recycler_view)
    RecyclerView shotsRecyclerView;

    @BindView(R.id.user_avatar)
    CircleImageView userAvatar;

    @BindView(R.id.user_name)
    TextView usernameText;

    @BindView(R.id.user_location)
    TextView userLocationText;

    @Inject
    UserPresenter userPresenter;

    private ShotsRecyclerAdapter shotsAdapter;
    private User user;

    public static Intent getIntentToMe(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(Constants.PARAM_USER, user);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (User) getIntent().getSerializableExtra(Constants.PARAM_USER);
        setTitle(user.getName());

        inflateUserInfo(user);

        shotsAdapter = new ShotsRecyclerAdapter(this, new ArrayList<Shot>(), null);

        shotsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        shotsRecyclerView.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.m_space)));
        shotsRecyclerView.setAdapter(shotsAdapter);

        userInfoHeader.attachTo(shotsRecyclerView);

        userPresenter.listUserShots(user.getId(), 0);
    }

    private void inflateUserInfo(User userInfo) {
        ImageLoader.loadImage(this, userAvatar, userInfo.getAvatarUrl());
        usernameText.setText(userInfo.getName());
        userLocationText.setText(userInfo.getLocation());
    }

    @Override
    protected void setupComponent(ZhihuAppComponent appComponent) {
        DaggerUserComponent.builder().zhihuAppComponent(appComponent)
                .userModule(new UserModule(this))
                .build().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_user;
    }

    @Override
    Presenter getPresenter() {
        return userPresenter;
    }

    @Override
    public void refreshList(List<Shot> data) {
        shotsAdapter.refreshList(data);
    }
}
