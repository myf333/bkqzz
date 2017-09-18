package com.myf.baokuqzz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.myf.baokuqzz.model.NewsView;

import java.util.List;

/**
 * Created by myf on 2017/9/18.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private List<NewsView> newsList;
    public NewsListAdapter() {
        super();
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

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
