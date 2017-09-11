package com.myf.baokuqzz.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by myf on 2017/8/17.
 */

public class NetworkUtil {
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected() ){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                    ||networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    ||networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
}
