package com.myf.baokuqzz.presenter;

import com.myf.baokuqzz.activity.RegisterLoginActivity;
import com.myf.baokuqzz.dao.LoginDao;

/**
 * Created by maoyanfeng on 2017/10/2.
 */

public class RegisterLoginPresenter extends BasePresenter<RegisterLoginActivity,LoginDao> {
    private RegisterLoginActivity activity;

    public RegisterLoginPresenter(RegisterLoginActivity activity) {
        this.activity = activity;
        attachView(activity,LoginDao.class);
    }
}
