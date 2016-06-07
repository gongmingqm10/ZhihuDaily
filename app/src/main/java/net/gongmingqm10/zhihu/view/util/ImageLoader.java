package net.gongmingqm10.zhihu.view.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {

    public static void loadImage(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);
    }
}
