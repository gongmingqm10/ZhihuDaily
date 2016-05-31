package net.gongmingqm10.zhihu.view.adapter.viewholder;

import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> {

    public BaseViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public abstract void populate(T data);
}
