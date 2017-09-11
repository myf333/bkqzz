package com.myf.baokuqzz.dao;

import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.ReturnRet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by myf on 2017/8/21.
 */

public interface MainDao {
    /**
     * 获取宝库动态列表
     * */
    @GET("Index/News")
    Observable<ReturnRet<List<NewsView>>> getNews(@Query("size") int size);
}
