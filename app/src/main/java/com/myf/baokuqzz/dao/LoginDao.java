package com.myf.baokuqzz.dao;

import com.myf.baokuqzz.model.RegUserView;
import com.myf.baokuqzz.model.ReturnRet;
import com.myf.baokuqzz.model.UserToken;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by maoyanfeng on 2017/10/2.
 */

public interface LoginDao {
    @FormUrlEncoded
    @POST("User/SendVerCode")

    Observable<ReturnRet<String>> postSengVerCode(@Field("phone")String phone);

    @FormUrlEncoded
    @POST("../Token")
    @Headers("Authorization:Basic YXBwXzc1Y2YydHdxOjNmODYwZDI3ZTZlMDQ2OWU4YWNiNWM5ZGQ5NTU4ODkw")
    Observable<ReturnRet<RegUserView>> postToken(@Field("username")String userName,
                                                 @Field("password")String password,
                                                 @Field("grant_type")String grantType);

    @FormUrlEncoded
    @POST("../Token")
    @Headers("Authorization:Basic YXBwXzc1Y2YydHdxOjNmODYwZDI3ZTZlMDQ2OWU4YWNiNWM5ZGQ5NTU4ODkw")
    Observable<ReturnRet<RegUserView>> postTokenValidCode(@Field("username")String userName,
                                                 @Field("validcode")String validCode,
                                                 @Field("grant_type")String grantType);

    @GET("User/Info")
    Observable<ReturnRet<RegUserView>> getInfo();

    @FormUrlEncoded
    @POST("User/SetToken")
    Observable<UserToken> postSetToken(@Field("token") String token, @Field("phoneType") String phoneType);

    @FormUrlEncoded
    @POST("User/CheckPhone")
    Observable<ReturnRet<Boolean>> postCheckPhone(@Field("phone") String phone);
}
