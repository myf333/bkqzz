package com.myf.baokuqzz.retrofit;

import com.myf.baokuqzz.global.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by myf on 2017/8/17.
 */

public class RetrofitClient {
    private static Retrofit retrofit;
    private RetrofitClient(){}
    static {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder().baseUrl(Config.ApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client).build();
    }

    public static Retrofit getInstance(){
        return retrofit;
    }
}
