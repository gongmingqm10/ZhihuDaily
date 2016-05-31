package net.gongmingqm10.zhihu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.gongmingqm10.zhihu.presenter.Presenter;
import net.gongmingqm10.zhihu.view.activity.BaseActivity;
import net.gongmingqm10.zhihu.view.adapter.viewholder.BaseViewHolder;

import java.util.List;

public abstract class SimpleListAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> data;

    public SimpleListAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public T getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<T> viewHolder = getViewHolder(position, convertView, parent);
        viewHolder.populate(getItem(position));
        return convertView;
    }

    abstract BaseViewHolder<T> getViewHolder(int position, View convertView, ViewGroup parent);
}
