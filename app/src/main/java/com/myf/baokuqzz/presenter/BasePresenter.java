package com.myf.baokuqzz.presenter;

import android.widget.Toast;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.retrofit.RetrofitClient;
import com.myf.baokuqzz.util.NetworkUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by myf on 2017/8/17.
 */

public abstract class BasePresenter<A,D> {
    private A activity;
    private D dao;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    void attachView(A activity,Class<D> classDao){
        this.activity = activity;
        dao = RetrofitClient.getInstance().create(classDao);
    }

    public void detachView(){
        this.activity = null;
        unSubscribe();
    }

    public <T> void addSubscription(Observable<T> observable, DisposableObserver<T> disposableObserver){
        if(NetworkUtil.isNetworkConnected(BKApplication.getInstance())){
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(disposableObserver);
            compositeSubscription.add(disposableObserver);
        }else {
            Toast.makeText(BKApplication.getInstance(), R.string.check_network,Toast.LENGTH_SHORT).show();
        }
    }

    private void unSubscribe(){
        compositeSubscription.clear();
    }

    public D getDao() {
        return dao;
    }
}
