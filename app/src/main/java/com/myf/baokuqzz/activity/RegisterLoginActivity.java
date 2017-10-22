package com.myf.baokuqzz.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.presenter.RegisterLoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterLoginActivity extends BaseActivity<RegisterLoginPresenter> {
    @Bind(R.id.tv_themeTitle)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        ButterKnife.bind(this);
        title.setText(R.string.register_login);
    }

    @Override
    protected RegisterLoginPresenter createPresenter() {
        return new RegisterLoginPresenter(this);
    }

    @OnClick({R.id.img_themeBack})
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.img_themeBack:
                finish();
                break;
        }
    }
}
