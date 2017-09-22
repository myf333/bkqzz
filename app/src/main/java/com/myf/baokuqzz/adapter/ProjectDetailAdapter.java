package com.myf.baokuqzz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.myf.baokuqzz.activity.ProjectActivity;

/**
 * Created by myf on 2017/9/22.
 */

public class ProjectDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ProjectActivity activity;

    public ProjectDetailAdapter(ProjectActivity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
