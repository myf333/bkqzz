package com.myf.baokuqzz.dao;

import com.myf.baokuqzz.model.NewsView;
import com.myf.baokuqzz.model.PageRet;
import com.myf.baokuqzz.model.ReturnRet;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by myf on 2017/9/18.
 */

public interface NewsDao {
    @GET("Index/NewsList")
    Observable<PageRet<List<NewsView>>> getNewsList(@Query("currpage")int currPage, @Query("pagesize")int pageSize);

    @GET("Index/NewsDetail")
    Observable<ReturnRet<NewsView>> getNewsDetail(@Query("id")long id);
}
