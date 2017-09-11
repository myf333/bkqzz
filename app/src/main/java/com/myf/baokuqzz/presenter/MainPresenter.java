package com.myf.baokuqzz.presenter;

import com.myf.baokuqzz.activity.MainActivity;
import com.myf.baokuqzz.dao.MainDao;

/**
 * Created by myf on 2017/8/21.
 *
 */

public class MainPresenter extends BasePresenter<MainActivity,MainDao> {

    public MainPresenter(MainActivity activity) {
        attachView(activity,MainDao.class);
    }


}
