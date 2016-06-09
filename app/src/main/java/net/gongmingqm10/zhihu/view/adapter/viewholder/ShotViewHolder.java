package net.gongmingqm10.zhihu.view.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
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

    @BindView(R.id.shot_user_panel)
    View userPanelView;

    @BindView(R.id.shot_card_view)
    CardView shotCardView;

    private ShotClickListener shotClickListener;

    public ShotViewHolder(Context context, View convertView, ShotClickListener shotClickListener) {
        super(context, convertView);
        this.shotClickListener = shotClickListener;
    }

    @Override
    public void populate(final Shot data) {
        if (data == null) return;

        bindClickListeners(data);

        ImageLoader.loadImage(context, shotImage, data.getImage().getNormal());

        shotViewCountText.setText(String.valueOf(data.getViewsCount()));
        shotLoveCountText.setText(String.valueOf(data.getLikesCount()));
        shotCommentsCountText.setText(String.valueOf(data.getCommentsCount()));

        if (data.getUser() == null) {
            authorAvatar.setVisibility(View.GONE);
            shotAuthorText.setVisibility(View.GONE);
        } else {
            authorAvatar.setVisibility(View.VISIBLE);
            shotAuthorText.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(context, authorAvatar, data.getUser().getAvatarUrl());
            shotAuthorText.setText(data.getUser().getName());
        }
    }

    private void bindClickListeners(final Shot data) {
        shotLoveCountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotClickListener != null) {
                    shotClickListener.likeShot(data);
                }
            }
        });
        authorAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotClickListener != null) {
                    shotClickListener.viewAuthor(data);
                }
            }
        });
        shotCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotClickListener != null) {
                    shotClickListener.viewShot(data);
                }
            }
        });
    }

    public interface ShotClickListener {
        void viewShot(Shot shot);

        void likeShot(Shot shot);

        void viewAuthor(Shot shot);
    }
}
