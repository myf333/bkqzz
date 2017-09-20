package com.myf.baokuqzz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.adapter.NewsListAdapter;
import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.presenter.NewsFragmentPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by myf on 2017/9/18.
 */

public class NewsFragment extends BaseFragment<NewsFragmentPresenter> implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private int currPage = 1;
    private int totalNum = 0;
    private  NewsListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bkcommunity,container,false);
        ButterKnife.bind(this,view);
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
        adapter = new NewsListAdapter();
        recyclerView.setAdapter(adapter);
        presenter.getNewsList(1);
    }

    @Override
    protected NewsFragmentPresenter createPresenter() {
        return new NewsFragmentPresenter((MainActivity) getActivity(),this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(swipeRefreshLayout.isRefreshing()){
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onRefresh() {
        presenter.getNewsList(1);
    }

    @Override
    public void onClick(View view) {

    }

    public void updateFail(){
        swipeRefreshLayout.setRefreshing(false);
    }

    public void getNewsList(List<NewsView> list,int currPage,int total){
        this.totalNum = total;
        this.currPage = currPage;
        swipeRefreshLayout.setRefreshing(false);
        adapter.setNewsList(list);
        adapter.notifyDataSetChanged();
    }
}
