package com.myf.baokuqzz.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myf.baokuqzz.presenter.BasePresenter;

/**
 * Created by myf on 2017/8/22.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements View.OnClickListener {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }
    protected abstract P createPresenter();

}
