package com.myf.baokuqzz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myf.baokuqzz.R;

import java.util.List;

/**
 * Created by myf on 2017/9/25.
 */

public class ProjectServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> serviceList;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_service,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServiceViewHolder myHolder = (ServiceViewHolder)holder;
        String service = serviceList.get(position);
        switch (service){
            case "物品保管":
                myHolder.service_img.setImageResource(R.drawable.sercive_baoguan);
                break;
            case "艺术会展":
                myHolder.service_img.setImageResource(R.drawable.service_huizhan);
                break;
            case "免费网络":
                myHolder.service_img.setImageResource(R.drawable.icon_wifi);
                break;
            case "茶饮休憩":
                myHolder.service_img.setImageResource(R.drawable.icon_rest);
                break;
            case "拍卖服务":
                myHolder.service_img.setImageResource(R.drawable.icon_paimai);
                break;
            case "贵宾通道":
                myHolder.service_img.setImageResource(R.drawable.service_guibin);
                break;
            case "活动场地":
                myHolder.service_img.setImageResource(R.drawable.service_huodong);
                break;
        }
        myHolder.service_text.setText(service);
    }

    @Override
    public int getItemCount() {
        if(serviceList!=null&&serviceList.size()>0){
            return serviceList.size();
        }
        return 0;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder{
        ImageView service_img;
        TextView service_text;
        public ServiceViewHolder(View itemView) {
            super(itemView);
            service_img = itemView.findViewById(R.id.img_project_service);
            service_text = itemView.findViewById(R.id.txt_project_service);
        }
    }
}
