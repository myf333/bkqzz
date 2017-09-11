package com.myf.baokuqzz.dao;

import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by myf on 2017/9/5.
 */

public interface MapDao {
    @GET("Project/CanSeeProject")
    Observable<ReturnRet<List<ProjectView>>> getCanSeeProject(@Query("city") String city);

    @GET("Project/CanSeeProject")
    Observable<ReturnRet<List<ProjectView>>> getCanSeeProjectXY(@Query("X1") String X1, @Query("Y1") String Y1, @Query("X2") String X2, @Query("Y2") String Y2);

    @GET("Project/MapList")
    Observable<ReturnRet<List<ProjectView>>> getProjectMapList(@Query("X") String X, @Query("Y") String Y);
}
