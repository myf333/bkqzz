package com.myf.baokuqzz.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.BaseActivity;
import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.adapter.BKCommunityAdapter;
import com.myf.baokuqzz.global.Config;
import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;
import com.myf.baokuqzz.presenter.BKCommunityPresenter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BKCommunityFragment extends BaseFragment<BKCommunityPresenter> implements SwipeRefreshLayout.OnRefreshListener {
    private BaseActivity activity;
    private BKCommunityAdapter bkCommunityAdapter;
    private double Longitude;
    private double Latitude;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bkcommunity,container,false);
        ButterKnife.bind(this,view);
        activity = (BaseActivity) getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.color_red));
        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        bkCommunityAdapter = new BKCommunityAdapter(activity);
        recyclerView.setAdapter(bkCommunityAdapter);

        presenter.loadNews(10);
        presenter.getNearBy(Config.Longitude, Config.Latitude);
    }

    public void updateNews(ReturnRet<List<NewsView>> model){
        bkCommunityAdapter.getNewsViews().clear();
        bkCommunityAdapter.getNewsViews().addAll(model.getData());
        bkCommunityAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void updateNewsFailure(){
        swipeRefreshLayout.setRefreshing(false);
    }

    public void updateNearByProject(ReturnRet<ProjectView> model){
        bkCommunityAdapter.setProjectView(model.getData());
        bkCommunityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden) {
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    protected BKCommunityPresenter createPresenter() {
        return new BKCommunityPresenter((MainActivity)getActivity(),this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        presenter.loadNews(10);
        if(Longitude>0 && Latitude>0){
            presenter.getNearBy(Longitude, Latitude);
        }else{
            presenter.getNearBy(Config.Longitude, Config.Latitude);
        }
    }

    public void setLocation(BDLocation location) {
        if (location != null && presenter != null) {
            if (location.getAddress() != null) {
                Latitude = location.getLatitude();
                Longitude = location.getLongitude();
                presenter.getNearBy(location.getLongitude(), location.getLatitude());
            } else {
                presenter.getNearBy(Config.Longitude, Config.Latitude);
            }

        } else if (location == null && presenter != null) {
            presenter.getNearBy(Config.Longitude, Config.Latitude);
        }
    }
}
