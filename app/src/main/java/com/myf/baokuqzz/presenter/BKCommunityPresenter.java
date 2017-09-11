package com.myf.baokuqzz.presenter;

import android.widget.Toast;

import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.dao.BKComunityDao;
import com.myf.baokuqzz.fragment.BKCommunityFragment;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;
import com.myf.baokuqzz.retrofit.ApiCallback;
import com.myf.baokuqzz.retrofit.DefaultApiCallback;

import java.util.List;

/**
 * Created by myf on 2017/8/22.
 */

public class BKCommunityPresenter extends BasePresenter<MainActivity,BKComunityDao> {
    private BKCommunityFragment fragment;
    public BKCommunityPresenter(MainActivity activity,BKCommunityFragment fragment){
        attachView(activity,BKComunityDao.class);
        this.fragment = fragment;
    }

    public void loadNews(int size){
        addSubscription(getDao().getNews(size), new ApiCallback<ReturnRet<List<NewsView>>>() {
            @Override
            public void onSuccess(ReturnRet<List<NewsView>> model) {
                if(model.isSuccess()){
                    fragment.updateNews(model);
                }else {
                    Toast.makeText(BKApplication.getInstance(),model.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                fragment.updateNewsFailure();
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getNearBy(double x, double y){
        addSubscription(getDao().getNearBy(x, y), new DefaultApiCallback<ReturnRet<ProjectView>>() {
            @Override
            public void onSuccess(ReturnRet<ProjectView> model) {
                fragment.updateNearByProject(model);
            }
        });
    }

    public void getMyBox(){

    }
}
