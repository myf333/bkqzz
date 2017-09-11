package com.myf.baokuqzz.retrofit;

import com.myf.baokuqzz.model.Token;
import com.myf.baokuqzz.util.SharedPreferencesUtil;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by myf on 2017/8/31.
 */

public class UrlSignInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Token token = SharedPreferencesUtil.getObject(Token.class);
        Request.Builder requestBuilder = request.newBuilder();
        if (token != null && token.getAccess_token() != null && token.getExpires_in() >= new Date().getTime())
            if (request.header("Authorization") == null)
                requestBuilder.addHeader("Authorization", "Bearer " + token.getAccess_token());
        request = requestBuilder.build();
        return chain.proceed(request);
    }
}
