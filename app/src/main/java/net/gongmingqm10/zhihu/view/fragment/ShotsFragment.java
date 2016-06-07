package net.gongmingqm10.zhihu.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerMainComponent;
import net.gongmingqm10.zhihu.dagger2.MainModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.presenter.ShotsPresenter;
import net.gongmingqm10.zhihu.view.adapter.ShotsRecyclerAdapter;
import net.gongmingqm10.zhihu.view.util.Constants;
import net.gongmingqm10.zhihu.view.util.SpacesItemDecoration;
import net.gongmingqm10.zhihu.view.util.StaggeredScrollBottomListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShotsFragment extends BaseFragment implements ShotsPresenter.ShotsView {

    @BindView(R.id.shots_recycler_view)
    RecyclerView shotsRecyclerView;

    @BindView(R.id.shots_refresh_layout)
    SwipeRefreshLayout shotsRefreshLayout;

    @Inject
    ShotsPresenter shotsPresenter;

    private ShotsRecyclerAdapter shotsListAdapter;
    private boolean isLoading = true;
    private boolean isFinished = false;
    private int currentPage = 0;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.shots);

        initShotsRecyclerView();

        initRefreshLayout();

        loading(getString(R.string.is_loading));
        shotsPresenter.loadShots(currentPage);
    }

    private void initShotsRecyclerView() {
        shotsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        shotsRecyclerView.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.m_space)));

        shotsListAdapter = new ShotsRecyclerAdapter(getActivity(), new ArrayList<Shot>());
        shotsRecyclerView.setAdapter(shotsListAdapter);
        shotsRecyclerView.addOnScrollListener(scrollBottomListener);
    }

    private void initRefreshLayout() {
        shotsRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                resetShotsStatus();
                shotsPresenter.loadShots(0);
            }
        });

        shotsRefreshLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isLoading;
            }
        });
    }

    private void resetShotsStatus() {
        currentPage = 0;
        isLoading = true;
        isFinished = false;
        shotsListAdapter.clear();
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
        loaded();
        shotsListAdapter.refreshList(data);
        isLoading = false;
        shotsRefreshLayout.setRefreshing(false);
        if (data.size() < Constants.PAGE_SIZE) {
            isFinished = true;
        }
    }

    private StaggeredScrollBottomListener scrollBottomListener = new StaggeredScrollBottomListener() {
        @Override
        public void scrollToBottom() {
            if (isLoading || isFinished) return;
            shotsPresenter.loadShots(++currentPage);
        }
    };
}
