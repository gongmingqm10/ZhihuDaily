package net.gongmingqm10.zhihu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Theme;
import net.gongmingqm10.zhihu.view.adapter.viewholder.BaseViewHolder;
import net.gongmingqm10.zhihu.view.adapter.viewholder.MainViewHolder;

import java.util.List;

public class MainMenuAdapter extends SimpleListAdapter<Theme> {

    public MainMenuAdapter(Context context, List<Theme> data) {
        super(context, data);
    }

    @Override
    BaseViewHolder<Theme> getViewHolder(int position, View convertView, ViewGroup parent) {
        MainViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.main_menu_item, parent, false);
            viewHolder = new MainViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MainViewHolder) convertView.getTag();
        }
        return viewHolder;
    }
}
