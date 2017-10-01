package com.myf.baokuqzz.dao;

import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.model.ReturnRet;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by myf on 2017/9/22.
 */

public interface ProjectDao {
    @GET("Project/ProjectDetail")
    Observable<ReturnRet<ProjectView>> getProjectDetail(@Query("id")int id);
}
