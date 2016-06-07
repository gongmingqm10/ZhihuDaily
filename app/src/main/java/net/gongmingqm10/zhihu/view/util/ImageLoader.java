package net.gongmingqm10.zhihu.view.util;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

public class ImageLoader {

    public static void loadImage(SimpleDraweeView imageView, String url) {
        imageView.setImageURI(Uri.parse(url));
    }
}
