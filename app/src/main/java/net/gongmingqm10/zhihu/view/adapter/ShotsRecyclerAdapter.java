package net.gongmingqm10.zhihu.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.gongmingqm10.zhihu.R;
import net.gongmingqm10.zhihu.model.Shot;
import net.gongmingqm10.zhihu.view.adapter.viewholder.ShotViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShotsRecyclerAdapter extends RecyclerView.Adapter<ShotViewHolder> {

    private Context context;
    private List<Shot> shots;
    private ShotViewHolder.ShotClickListener shotClickListener;

    public ShotsRecyclerAdapter(Context context, List<Shot> shots, ShotViewHolder.ShotClickListener shotClickListener) {
        this.context = context;
        this.shots = shots;
        this.shotClickListener = shotClickListener;
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_shot, parent, false);
        return new ShotViewHolder(context, convertView, shotClickListener);
    }

    @Override
    public void onBindViewHolder(ShotViewHolder holder, int position) {
        holder.populate(shots.get(position));
    }

    @Override
    public int getItemCount() {
        return shots == null ? 0 : shots.size();
    }

    public void refreshList(List<Shot> data) {
        if (shots == null) {
            shots = new ArrayList<>();
        }
        shots.addAll(data);
        this.notifyDataSetChanged();
    }

    public void clear() {
        if (shots == null) {
            shots = new ArrayList<>();
        }
        shots.clear();
    }
}
