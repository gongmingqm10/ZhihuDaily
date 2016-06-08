package net.gongmingqm10.zhihu.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Comment;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.view.adapter.viewholder.BaseViewHolder;
import net.gongmingqm10.zhihu.view.adapter.viewholder.ShotCommentViewHolder;
import net.gongmingqm10.zhihu.view.adapter.viewholder.ShotInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShotDetailAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private Shot shot;
    private List<Comment> comments;

    private static final int VIEW_TYPE_INFO = 1;
    private static final int VIEW_TYPE_COMMENT = 2;

    public ShotDetailAdapter(Context context) {
        this.context = context;
        comments = new ArrayList<>();
    }

    public void showShot(Shot shot) {
        this.shot = shot;
        notifyDataSetChanged();
    }

    public void showComments(List<Comment> data) {
        comments.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_INFO) {
            View infoView = LayoutInflater.from(context).inflate(R.layout.item_shot_info, parent, false);
            return new ShotInfoViewHolder(context, infoView);
        } else {
            View commentView = LayoutInflater.from(context).inflate(R.layout.item_shot_comment, parent, false);
            return new ShotCommentViewHolder(context, commentView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_INFO : VIEW_TYPE_COMMENT;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof ShotInfoViewHolder) {
            ((ShotInfoViewHolder) holder).populate(shot);
        } else if (holder instanceof ShotCommentViewHolder) {
            ((ShotCommentViewHolder) holder).populate(comments.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + 1;
    }
}
