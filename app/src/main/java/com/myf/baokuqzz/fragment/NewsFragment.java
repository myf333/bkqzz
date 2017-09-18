package com.myf.baokuqzz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.presenter.NewsFragmentPresenter;

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
    }

    @Override
    protected NewsFragmentPresenter createPresenter() {
        return new NewsFragmentPresenter((MainActivity) getActivity(),this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View view) {

    }
}
