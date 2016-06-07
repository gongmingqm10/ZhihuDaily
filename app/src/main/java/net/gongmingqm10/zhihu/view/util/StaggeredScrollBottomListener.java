package net.gongmingqm10.zhihu.view.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class StaggeredScrollBottomListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int[] firstVisiblePositions = ((StaggeredGridLayoutManager)(recyclerView.getLayoutManager())).findFirstVisibleItemPositions(null);
        if (!recyclerView.canScrollVertically(1) && firstVisiblePositions[0] != 0) {
            scrollToBottom();
        }
    }

    public abstract void scrollToBottom();
}
