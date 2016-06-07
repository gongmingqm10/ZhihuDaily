package net.gongmingqm10.zhihu.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerMainComponent;
import net.gongmingqm10.zhihu.dagger2.MainModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.presenter.ShotsPresenter;
import net.gongmingqm10.zhihu.view.adapter.ShotsRecyclerAdapter;
import net.gongmingqm10.zhihu.view.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShotsFragment extends BaseFragment implements ShotsPresenter.ShotsView {

    @BindView(R.id.shots_recycler_view)
    RecyclerView shotsRecyclerView;

    @Inject
    ShotsPresenter shotsPresenter;

    private ShotsRecyclerAdapter shotsListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.shots);

        shotsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        shotsRecyclerView.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.m_space)));

        shotsListAdapter = new ShotsRecyclerAdapter(getActivity(), new ArrayList<Shot>());
        shotsRecyclerView.setAdapter(shotsListAdapter);

        shotsPresenter.loadShots(0);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shots;
    }

    @Override
    protected void setupComponent(ZhihuAppComponent appComponent) {
        DaggerMainComponent.builder().zhihuAppComponent(appComponent)
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    public void refreshList(List<Shot> data) {
        shotsListAdapter.refreshList(data);
    }
}
