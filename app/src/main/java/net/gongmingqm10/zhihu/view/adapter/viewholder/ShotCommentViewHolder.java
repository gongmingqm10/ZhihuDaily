package net.gongmingqm10.zhihu.view.adapter.viewholder;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Comment;
import net.gongmingqm10.zhihu.view.util.ImageLoader;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShotCommentViewHolder extends BaseViewHolder<Comment> {

    @BindView(R.id.shot_user_avatar)
    CircleImageView userAvatar;

    @BindView(R.id.shot_user_name)
    TextView usermameText;

    @BindView(R.id.shot_comment_content)
    TextView contentText;

    @BindView(R.id.comment_love_text)
    TextView commentLikeText;


    public ShotCommentViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void populate(Comment data) {
        if (data == null) return;

        ImageLoader.loadImage(context, userAvatar, data.getUser().getAvatarUrl());
        usermameText.setText(data.getUser().getName());
        contentText.setText(Html.fromHtml(data.getBody().replace("\n", " ")));
        commentLikeText.setText(String.valueOf(data.getLikesCount()));
    }
}
