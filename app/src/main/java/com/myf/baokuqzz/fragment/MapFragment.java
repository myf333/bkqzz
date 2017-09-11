package com.myf.baokuqzz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.adapter.MapListAdapter;
import com.myf.baokuqzz.adapter.MapPagerAdapter;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;
import com.myf.baokuqzz.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myf on 2017/9/5.
 */

public class MapFragment extends BaseFragment<MapPresenter> implements SwipeRefreshLayout.OnRefreshListener{
    private View mapFragmentView;
    private LayoutInflater layoutInflater;
    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<>();
    private MapView mapView;
    private BaiduMap baiduMap;
    private MapListAdapter mapListAdapter;
    private SwipeRefreshLayout swipe_refresh_map_list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mapFragmentView = inflater.inflate(R.layout.fragment_map,container,false);
        layoutInflater = inflater;
        return mapFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager = mapFragmentView.findViewById(R.id.mapViewPager);
        View view1 = layoutInflater.inflate(R.layout.fragment_map_list,null);
        viewList.add(view1);
        swipe_refresh_map_list = view1.findViewById(R.id.swipe_refresh_map_list);
        swipe_refresh_map_list.setColorSchemeColors(getResources().getColor(R.color.color_red));
        swipe_refresh_map_list.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView recyclerView = view1.findViewById(R.id.recycler_map_list);
        mapListAdapter = new MapListAdapter(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mapListAdapter);

        View view2 = layoutInflater.inflate(R.layout.fragment_map_map,null);
        viewList.add(view2);
        MapPagerAdapter pagerAdapter = new MapPagerAdapter(viewList);
        viewPager.setAdapter(pagerAdapter);
        mapView = view2.findViewById(R.id.bMapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
    }

    @Override
    protected MapPresenter createPresenter() {
        return new MapPresenter((MainActivity)getActivity(),this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(swipe_refresh_map_list.isRefreshing()){
                swipe_refresh_map_list.setRefreshing(false);
            }
        }
    }

    @Override
    public void onRefresh() {
        presenter.getCanSeeProject("上海");
    }

    public void loadDate(){
        presenter.getCanSeeProject("上海");
    }

    public void updateList(ReturnRet<List<ProjectView>> model){
        if(model.getData().size()>0)
        {
            for(int position=0;position<model.getData().size();position++){
                if(model.getData().get(position).getProjectstatus().equals("在建项目")){
                    mapListAdapter.setTitleIndex(position);
                    break;
                }
            }
        }
        mapListAdapter.setProjects(model.getData());
        mapListAdapter.notifyDataSetChanged();
        swipe_refresh_map_list.setRefreshing(false);
    }
}
