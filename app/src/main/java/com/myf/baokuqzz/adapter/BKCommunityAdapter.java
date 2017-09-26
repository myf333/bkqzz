package com.myf.baokuqzz.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.BaseActivity;
import com.myf.baokuqzz.activity.NewsDetailActivity;
import com.myf.baokuqzz.activity.ProjectActivity;
import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.ProjectView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by myf on 2017/8/24.
 */

public class BKCommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_NEARBY=0;
    private static final int TYPE_NEWS_TITLE=1;
    private static final int TYPE_NEWS=2;
    private static final int DEFAULT_ITEM_SIZE = 2;
    private List<NewsView> newsViews = new ArrayList<>();
    private ProjectView projectView;
    private BaseActivity activity;

    public BKCommunityAdapter(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_NEARBY;
        }
        if(position == 1){
            return TYPE_NEWS_TITLE;
        }
        if(position == 2){
            return TYPE_NEWS;
        }
        return TYPE_NEWS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_NEARBY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby,parent,false);
                return new NearbyHolder(view);
            case TYPE_NEWS_TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_title,parent,false);
                return new NewsTitleHolder(view);
            case TYPE_NEWS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
                return new NewsHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
                return new NewsHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(getItemViewType(position)){
            case TYPE_NEARBY:
                NearbyHolder nearbyHolder = (NearbyHolder)holder;
                if(projectView!=null){
                    nearbyHolder.txt_project_name.setText(projectView.getName());
                    DecimalFormat df = new DecimalFormat("######0.00");
                    String distance = df.format(projectView.getDistance())+"KM";
                    nearbyHolder.txt_project_distance.setText(distance);
                    nearbyHolder.txt_project_address.setText(projectView.getAddress());
                    nearbyHolder.item_project_more.setOnClickListener(activity);
                    nearbyHolder.id = projectView.getId();
                }
                break;
            case TYPE_NEWS_TITLE:
                //NewsTitleHolder newsTitleHolder = (NewsTitleHolder)holder;
                //newsTitleHolder.item_news_more.setOnClickListener();
                break;
            case TYPE_NEWS:
                NewsHolder newsHolder = (NewsHolder)holder;
                NewsView newsView = newsViews.get(position-DEFAULT_ITEM_SIZE);
                newsHolder.txt_news_title.setText(newsView.getTitle());
                newsHolder.txt_news_content.setText(newsView.getMemo());
                newsHolder.txt_news_time.setText(newsView.getPublishdate());
                newsHolder.id = newsView.getId();
                if(newsView.getPicurl()!=null&&!newsView.getPicurl().equals("")){
                    newsHolder.item_news_pic.setImageURI(newsView.getPicurl());
                }else {
                    newsHolder.item_news_pic.setImageResource(R.drawable.bg_home);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(newsViews.size()>0){
            return newsViews.size()+DEFAULT_ITEM_SIZE;
        }else {
            return DEFAULT_ITEM_SIZE;
        }
    }

    class NewsTitleHolder extends RecyclerView.ViewHolder{
        LinearLayout item_news_more;
        public NewsTitleHolder(View itemView) {
            super(itemView);
            item_news_more = itemView.findViewById(R.id.item_news_more);
            item_news_more.setOnClickListener(activity);
        }
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

    class NearbyHolder extends RecyclerView.ViewHolder{
        LinearLayout item_project_more;
        RelativeLayout item_project_detail;
        TextView txt_project_name;
        TextView txt_project_distance;
        TextView txt_project_address;
        int id;
        public NearbyHolder(View itemView) {
            super(itemView);
            item_project_more = itemView.findViewById(R.id.item_project_more);
            item_project_detail = itemView.findViewById(R.id.item_project_detail);
            txt_project_name = itemView.findViewById(R.id.txt_project_name);
            txt_project_distance = itemView.findViewById(R.id.txt_project_distance);
            txt_project_address = itemView.findViewById(R.id.txt_project_address);
            item_project_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ProjectActivity.class);
                    intent.putExtra("id",id);
                    activity.startActivity(intent);
                }
            });
        }
    }

    public List<NewsView> getNewsViews() {
        return newsViews;
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
    }
}
