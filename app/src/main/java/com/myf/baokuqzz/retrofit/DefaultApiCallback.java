package com.myf.baokuqzz.retrofit;

import android.widget.Toast;

import com.myf.baokuqzz.global.BKApplication;

/**
 * Created by myf on 2017/8/31.
 */

public abstract class DefaultApiCallback<M> extends ApiCallback<M> {

    @Override
    public void onFailure(String msg) {
        Toast.makeText(BKApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {

    }
}
