package com.myf.baokuqzz.retrofit;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by myf on 2017/8/31.
 */

public abstract class ApiCallback<M> extends DisposableObserver<M> {
    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onNext(M value) {
        onSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();

            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
