package com.myf.baokuqzz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.model.NewsView;

import java.util.List;

/**
 * Created by myf on 2017/9/18.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private List<NewsView> newsList;

    @Override
    public BKCommunityAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new BKCommunityAdapter.NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BKCommunityAdapter.NewsHolder newsHolder = (BKCommunityAdapter.NewsHolder)holder;
        NewsView newsView = newsList.get(position);
        newsHolder.txt_news_title.setText(newsView.getTitle());
        newsHolder.txt_news_content.setText(newsView.getMemo());
        newsHolder.txt_news_time.setText(newsView.getPublishdate());
        newsHolder.id = newsView.getId();
        if(newsView.getPicurl()!=null&&!newsView.getPicurl().equals("")){
            newsHolder.item_news_pic.setImageURI(newsView.getPicurl());
        }else {
            newsHolder.item_news_pic.setImageResource(R.drawable.bg_home);
        }
    }

    @Override
    public int getItemCount() {
        if(newsList!=null){
            return newsList.size();
        }
        return  0;
    }

    public void setNewsList(List<NewsView> newsList) {
        this.newsList = newsList;
    }
}
