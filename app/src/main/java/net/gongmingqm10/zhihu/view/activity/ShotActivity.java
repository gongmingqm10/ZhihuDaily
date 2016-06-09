package net.gongmingqm10.zhihu.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerShotComponent;
import net.gongmingqm10.zhihu.dagger2.ShotModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.model.Comment;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.presenter.Presenter;
import net.gongmingqm10.zhihu.presenter.ShotPresenter;
import net.gongmingqm10.zhihu.view.adapter.ShotDetailAdapter;
import net.gongmingqm10.zhihu.view.util.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class ShotActivity extends BaseActivity implements ShotPresenter.ShotView {

    @Inject
    ShotPresenter shotPresenter;

    @BindView(R.id.shot_detail_list)
    RecyclerView shotDetailList;

    private ShotDetailAdapter shotAdapter;

    public static Intent getIntentToMe(Context context, int shotId, String shotTitle) {
        Intent intent = new Intent(context, ShotActivity.class);
        intent.putExtra(Constants.PARAM_SHOT_ID, shotId);
        intent.putExtra(Constants.PARAM_SHOT_TITLE, shotTitle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int shotId = getIntent().getIntExtra(Constants.PARAM_SHOT_ID, 0);

        setTitle(getIntent().getStringExtra(Constants.PARAM_SHOT_TITLE));

        shotDetailList.setLayoutManager(new LinearLayoutManager(this));
        shotAdapter = new ShotDetailAdapter(this);
        shotDetailList.setAdapter(shotAdapter);

        shotPresenter.loadShot(shotId);
    }

    @Override
    protected void setupComponent(ZhihuAppComponent appComponent) {
        DaggerShotComponent.builder().zhihuAppComponent(appComponent)
                .shotModule(new ShotModule(this))
                .build().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_shot;
    }

    @Override
    Presenter getPresenter() {
        return shotPresenter;
    }

    @Override
    public void showShot(Shot shot) {
        shotAdapter.showShot(shot);
    }

    @Override
    public void showComments(List<Comment> comments) {
        shotAdapter.showComments(comments);
    }
}
