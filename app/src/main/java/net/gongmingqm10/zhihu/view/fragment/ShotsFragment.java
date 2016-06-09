package net.gongmingqm10.zhihu.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.dagger2.DaggerMainComponent;
import net.gongmingqm10.zhihu.dagger2.MainModule;
import net.gongmingqm10.zhihu.dagger2.ZhihuAppComponent;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.presenter.ShotsPresenter;
import net.gongmingqm10.zhihu.view.activity.ShotActivity;
import net.gongmingqm10.zhihu.view.activity.UserActivity;
import net.gongmingqm10.zhihu.view.adapter.ShotsRecyclerAdapter;
import net.gongmingqm10.zhihu.view.adapter.viewholder.ShotViewHolder;
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

    private PopupMenu popupMenu;
    private ShotsRecyclerAdapter shotsListAdapter;
    private boolean isLoading = true;
    private boolean isFinished = false;
    private int currentPage = 0;
    private Sort sort = Sort.POPULAR;

    public static ShotsFragment newInstance() {
        return new ShotsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.shots);

        initShotsRecyclerView();

        initRefreshLayout();

        loading(getString(R.string.is_loading));

        loadShots();
    }

    private void initShotsRecyclerView() {
        shotsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        shotsRecyclerView.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.m_space)));

        shotsListAdapter = new ShotsRecyclerAdapter(getActivity(), new ArrayList<Shot>(), shotClickListener);
        shotsRecyclerView.setAdapter(shotsListAdapter);
        shotsRecyclerView.addOnScrollListener(scrollBottomListener);
    }

    private void initRefreshLayout() {
        shotsRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                resetShotsStatus();
                shotsPresenter.loadShots(0, sort.getValue());
            }
        });

        shotsRefreshLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isLoading;
            }
        });
    }

    private void initSortDialog() {
        popupMenu = new PopupMenu(getActivity(), getActivity().findViewById(R.id.item_sort));
        popupMenu.getMenuInflater().inflate(R.menu.shots_sort_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(sortMenuItemClickListener);
    }

    private void resetShotsStatus() {
        sort = Sort.POPULAR;
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

    private void loadShots() {
        shotsPresenter.loadShots(currentPage, sort.getValue());
    }

    private StaggeredScrollBottomListener scrollBottomListener = new StaggeredScrollBottomListener() {
        @Override
        public void scrollToBottom() {
            if (isLoading || isFinished) return;
            currentPage++;
            loadShots();
        }
    };

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.item_sort).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_sort) {
            if (popupMenu == null) {
                initSortDialog();
            }
            popupMenu.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private PopupMenu.OnMenuItemClickListener sortMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.sort_popular:
                    sortByType(Sort.POPULAR);
                    return true;
                case R.id.sort_recent:
                    sortByType(Sort.RECENT);
                    return true;
                case R.id.sort_most_viewed:
                    sortByType(Sort.VIEWS);
                    return true;
                case R.id.sort_most_commented:
                    sortByType(Sort.COMMENTS);
                    return true;
                default:
                    return false;
            }
        }
    };

    private void sortByType(Sort currentType) {
        if (sort == currentType) return;

        resetShotsStatus();
        sort = currentType;
        loadShots();
    }

    private enum Sort {
        POPULAR(""), RECENT("recent"), VIEWS("views"), COMMENTS("comments");
        private String value;

        Sort(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private ShotViewHolder.ShotClickListener shotClickListener = new ShotViewHolder.ShotClickListener() {
        @Override
        public void viewShot(Shot shot) {
            // User view this Shot
            startActivity(ShotActivity.getIntentToMe(getActivity(), shot.getId(), shot.getTitle()));
        }

        @Override
        public void likeShot(Shot shot) {
            // User Liked this Shot
        }

        @Override
        public void viewAuthor(Shot shot) {
            // User click the Author Panel
            if (shot.getUser() == null) {
                showToast(R.string.user_not_found);
            } else {
                startActivity(UserActivity.getIntentToMe(getActivity(), shot.getUser()));
            }
        }
    };
}
