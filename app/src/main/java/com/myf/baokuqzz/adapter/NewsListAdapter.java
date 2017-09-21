package com.myf.baokuqzz.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.BaseActivity;
import com.myf.baokuqzz.activity.NewsDetailActivity;
import com.myf.baokuqzz.model.NewsView;

import java.util.List;

/**
 * Created by myf on 2017/9/18.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private List<NewsView> newsList;
    private BaseActivity activity;

    public NewsListAdapter(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsHolder newsHolder = (NewsHolder)holder;
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

    class NewsHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView item_news_pic;
        TextView txt_news_title;
        TextView txt_news_content;
        TextView txt_news_time;
        LinearLayout item_news;
        long id;
        public NewsHolder(View itemView) {
            super(itemView);
            item_news_pic = itemView.findViewById(R.id.item_news_pic);
            txt_news_title = itemView.findViewById(R.id.txt_news_title);
            txt_news_content = itemView.findViewById(R.id.txt_news_content);
            txt_news_time = itemView.findViewById(R.id.txt_news_time);
            item_news = itemView.findViewById(R.id.item_news);
            item_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, NewsDetailActivity.class);
                    intent.putExtra("id",id);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
