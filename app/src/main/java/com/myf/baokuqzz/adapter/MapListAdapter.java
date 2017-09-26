package com.myf.baokuqzz.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.ProjectActivity;
import com.myf.baokuqzz.model.ProjectView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by myf on 2017/9/8.
 */

public class MapListAdapter extends RecyclerView.Adapter {
    private List<ProjectView> projects = new ArrayList<>();
    private int titleIndex=0;
    private Activity activity;

    public MapListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_map_list,parent,false);
        return new MapListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProjectView project = projects.get(position);
        MapListViewHolder myHolder = (MapListViewHolder)holder;
        myHolder.txt_map_list_name.setText(project.getName());
        myHolder.txt_map_list_address.setText(project.getAddress());
        DecimalFormat df = new DecimalFormat("######0.00");
        String distance = "距离"+df.format(project.getDistance())+"KM";
        myHolder.txt_map_list_distance.setText(distance);
        myHolder.txt_map_list_type.setText(project.getProjecttype());
        if(project.getProjecttype().equals("社区宝库")){
            myHolder.img_map_list_mark.setImageResource(R.drawable.icon_maker1);
        }else{
            myHolder.img_map_list_mark.setImageResource(R.drawable.icon_maker2);
        }
        myHolder.txt_item_map_list_title.setText(project.getProjectstatus());
        if(position == 0 || position == titleIndex){
            myHolder.item_map_list_title.setVisibility(View.VISIBLE);
        }else {
            myHolder.item_map_list_title.setVisibility(View.GONE);
        }
        myHolder.id = project.getId();
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }


    private class MapListViewHolder extends RecyclerView.ViewHolder{
        LinearLayout item_map_list_title;
        TextView txt_item_map_list_title;
        TextView txt_map_list_name;
        TextView txt_map_list_address;
        TextView txt_map_list_distance;
        ImageView img_map_list_mark;
        TextView txt_map_list_type;
        LinearLayout btn_map_list_detail;
        LinearLayout btn_map_list_road;
        int id;
        MapListViewHolder(View itemView) {
            super(itemView);
            item_map_list_title = itemView.findViewById(R.id.item_map_list_title);
            txt_item_map_list_title = itemView.findViewById(R.id.txt_item_map_list_title);
            txt_map_list_name = itemView.findViewById(R.id.txt_map_list_name);
            txt_map_list_address = itemView.findViewById(R.id.txt_map_list_address);
            txt_map_list_distance = itemView.findViewById(R.id.txt_map_list_distance);
            img_map_list_mark = itemView.findViewById(R.id.img_map_list_mark);
            txt_map_list_type = itemView.findViewById(R.id.txt_map_list_type);
            btn_map_list_detail = itemView.findViewById(R.id.btn_map_list_detail);
            btn_map_list_road = itemView.findViewById(R.id.btn_map_list_road);
            btn_map_list_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ProjectActivity.class);
                    intent.putExtra("id",id);
                    activity.startActivity(intent);
                }
            });
        }
    }

    public void setProjects(List<ProjectView> projects) {
        this.projects = projects;
    }

    public void setTitleIndex(int titleIndex) {
        this.titleIndex = titleIndex;
    }
}
