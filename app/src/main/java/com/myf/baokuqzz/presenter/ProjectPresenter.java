package com.myf.baokuqzz.presenter;

import android.widget.Toast;

import com.myf.baokuqzz.activity.ProjectActivity;
import com.myf.baokuqzz.dao.ProjectDao;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;
import com.myf.baokuqzz.retrofit.DefaultApiCallback;

/**
 * Created by myf on 2017/9/22.
 */

public class ProjectPresenter extends BasePresenter<ProjectActivity,ProjectDao> {
    private ProjectActivity activity;

    public ProjectPresenter(ProjectActivity activity) {
        this.activity = activity;
    }

    public void getProjectDetail(int id){
        addSubscription(getDao().getProjectDetail(id), new DefaultApiCallback<ReturnRet<ProjectView>>() {
            @Override
            public void onSuccess(ReturnRet<ProjectView> model) {
                if(model.isSuccess()) {
                    activity.getProjectDetail(model.getData());
                }else {
                    Toast.makeText(BKApplication.getInstance(),model.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
