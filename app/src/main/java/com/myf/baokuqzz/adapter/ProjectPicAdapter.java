package com.myf.baokuqzz.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.model.ProjectPicView;

import java.util.List;

/**
 * Created by myf on 2017/9/22.
 */

public class ProjectPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ProjectPicView> picList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_pic,parent,false);
        return new PicItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProjectPicView pic = picList.get(position);
        PicItemHolder myHolder = (PicItemHolder)holder;
        myHolder.project_pic_title.setText(pic.getTitle());
        myHolder.img_project_pic.setImageURI(Uri.parse(pic.getPicurl()));
    }

    @Override
    public int getItemCount() {
        if(picList!=null){
            return picList.size();
        }
        return 0;
    }

    public void setPicList(List<ProjectPicView> picList) {
        this.picList = picList;
    }

    class PicItemHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView img_project_pic;
        TextView project_pic_title;
        public PicItemHolder(View itemView) {
            super(itemView);
            img_project_pic = itemView.findViewById(R.id.img_project_pic);
            project_pic_title = itemView.findViewById(R.id.project_pic_title);
        }
    }
}
