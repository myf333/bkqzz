package com.myf.baokuqzz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {

    }
}
