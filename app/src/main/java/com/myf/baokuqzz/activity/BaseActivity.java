package com.myf.baokuqzz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myf.baokuqzz.model.RegUserView;
import com.myf.baokuqzz.model.Token;
import com.myf.baokuqzz.presenter.BasePresenter;
import com.myf.baokuqzz.util.SharedPreferencesUtil;

import java.util.Date;

/**
 * Created by myf on 2017/8/18.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener {
    protected P presenter;
    public static boolean isForeground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter !=null){
            presenter.detachView();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static boolean hasLogin(){
        Token token = SharedPreferencesUtil.getObject(Token.class);
        if(token!=null&&token.getExpires_in()>new Date().getTime()){
            RegUserView user = SharedPreferencesUtil.getObject(RegUserView.class);
            if(user!=null){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public static boolean isValidName(){
        RegUserView user = SharedPreferencesUtil.getObject(RegUserView.class);
        if(user != null && user.getIsvalidname()){
            return true;
        }else {
            return false;
        }
    }
}
