package net.gongmingqm10.zhihu.view.adapter.viewholder;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.view.util.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShotInfoViewHolder extends BaseViewHolder<Shot> {

    @BindView(R.id.shot_author_avatar)
    CircleImageView authorAvatar;

    @BindView(R.id.shot_title)
    TextView shotTitleText;

    @BindView(R.id.shot_author_info)
    TextView authorInfoText;

    @BindView(R.id.shot_image)
    ImageView shotImage;

    @BindView(R.id.shot_view_text)
    TextView shotViewText;

    @BindView(R.id.shot_comment_text)
    TextView shotCommentText;

    @BindView(R.id.shot_love_text)
    TextView shotLoveText;

    @BindView(R.id.shot_description)
    TextView shotDescText;

    public ShotInfoViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void populate(Shot data) {
        if (data == null) return;
        ImageLoader.loadImage(context, authorAvatar, data.getUser().getAvatarUrl());
        ImageLoader.loadImage(context, shotImage, data.getImage().getHidpi());
        authorInfoText.setText(Html.fromHtml(context.getString(R.string.format_author_info, data.getUser().getName(), formatDate(data.getCreatedAt()))));
        shotTitleText.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getDescription())) {
            shotDescText.setText(Html.fromHtml(data.getDescription()));
        }
        shotViewText.setText(String.valueOf(data.getViewsCount()));
        shotLoveText.setText(String.valueOf(data.getLikesCount()));
        shotCommentText.setText(String.valueOf(data.getCommentsCount()));
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        return sdf.format(date);
    }
}
