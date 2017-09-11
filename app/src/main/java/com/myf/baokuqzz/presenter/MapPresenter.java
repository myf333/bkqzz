package com.myf.baokuqzz.presenter;

import android.widget.Toast;

import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.dao.MapDao;
import com.myf.baokuqzz.fragment.MapFragment;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;
import com.myf.baokuqzz.retrofit.DefaultApiCallback;

import java.util.List;

/**
 * Created by myf on 2017/9/5.
 */

public class MapPresenter extends BasePresenter<MainActivity,MapDao> {
    private MapFragment fragment;
    public MapPresenter(MainActivity activity,MapFragment fragment){
        attachView(activity,MapDao.class);
        this.fragment = fragment;
    }

    public void getCanSeeProject(String city){
        addSubscription(getDao().getCanSeeProject(city), new DefaultApiCallback<ReturnRet<List<ProjectView>>>() {
            @Override
            public void onSuccess(ReturnRet<List<ProjectView>> model) {
                if(model.isSuccess()){
                    fragment.updateList(model);
                }else {
                    Toast.makeText(BKApplication.getInstance(),model.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getProjectMapList(){

    }
}
