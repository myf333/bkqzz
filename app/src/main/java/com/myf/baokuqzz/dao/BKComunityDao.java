package com.myf.baokuqzz.dao;

import com.myf.baokuqzz.model.MyBox;
import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by myf on 2017/8/22.
 */

public interface BKComunityDao {
    @GET("Index/News")
    Observable<ReturnRet<List<NewsView>>> getNews(@Query("size") int size);

    @GET("Project/NearBy")
    Observable<ReturnRet<ProjectView>> getNearBy(@Query("X") double x, @Query("Y") double y);

    @GET("Order/MyBox")
    Observable<ReturnRet<List<MyBox>>> getMyBox();
}
