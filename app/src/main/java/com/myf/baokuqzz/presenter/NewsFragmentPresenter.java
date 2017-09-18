package com.myf.baokuqzz.presenter;

import android.widget.Toast;

import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.dao.NewsDao;
import com.myf.baokuqzz.fragment.NewsFragment;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.PageRet;
import com.myf.baokuqzz.retrofit.ApiCallback;

import java.util.List;

/**
 * Created by myf on 2017/9/18.
 */

public class NewsFragmentPresenter extends BasePresenter<MainActivity,NewsDao> {
    private NewsFragment fragment;

    public NewsFragmentPresenter(MainActivity activity,NewsFragment fragment) {
        this.fragment = fragment;
        attachView(activity,NewsDao.class);
    }

    public void getNewsList(int currPage){
        addSubscription(getDao().getNewsList(currPage, 5), new ApiCallback<PageRet<List<NewsView>>>() {
            @Override
            public void onSuccess(PageRet<List<NewsView>> model) {

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(BKApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
