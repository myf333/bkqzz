package com.myf.baokuqzz.presenter;

import com.myf.baokuqzz.activity.BaseActivity;
import com.myf.baokuqzz.activity.LoginActivity;
import com.myf.baokuqzz.dao.LoginDao;

/**
 * Created by maoyanfeng on 2017/10/2.
 */

public class LoginPresenter extends BasePresenter<LoginActivity,LoginDao> {
    private LoginActivity activity;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
        attachView(activity,LoginDao.class);
    }
}
