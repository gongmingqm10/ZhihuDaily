package net.gongmingqm10.zhihu.view.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.view.util.ImageLoader;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShotViewHolder extends BaseViewHolder<Shot> {
    @BindView(R.id.shot_image)
    ImageView shotImage;

    @BindView(R.id.shot_author_avatar)
    CircleImageView authorAvatar;

    @BindView(R.id.shot_view_text)
    TextView shotViewCountText;

    @BindView(R.id.shot_love_text)
    TextView shotLoveCountText;

    @BindView(R.id.shot_comment_text)
    TextView shotCommentsCountText;

    @BindView(R.id.shot_author_name)
    TextView shotAuthorText;

    public ShotViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void populate(Shot data) {
        ImageLoader.loadImage(context, shotImage, data.getImage().getNormal());
        ImageLoader.loadImage(context, authorAvatar, data.getUser().getAvatarUrl());
        shotViewCountText.setText(String.valueOf(data.getViewsCount()));
        shotLoveCountText.setText(String.valueOf(data.getLikesCount()));
        shotCommentsCountText.setText(String.valueOf(data.getCommentsCount()));
        shotAuthorText.setText(data.getUser().getName());
    }
}
