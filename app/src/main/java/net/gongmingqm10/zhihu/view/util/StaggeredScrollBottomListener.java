package net.gongmingqm10.zhihu.view.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class StaggeredScrollBottomListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int firstVisiblePosition = ((GridLayoutManager)(recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
        if (!recyclerView.canScrollVertically(1) && firstVisiblePosition != 0) {
            scrollToBottom();
        }
    }

    public abstract void scrollToBottom();
}
