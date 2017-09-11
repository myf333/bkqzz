package com.myf.baokuqzz.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.myf.baokuqzz.global.BKApplication;

/**
 * Created by myf on 2017/8/18.
 */

public class SharedPreferencesUtil {
    public static <T> void saveObject(T instance){
        SharedPreferences sp = BKApplication.getInstance().getSharedPreferences("SP",BKApplication.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(instance);
        editor.putString(instance.getClass().toString(),json);
        editor.apply();
    }

    public static <T> T getObject(Class<T> tClass){
        SharedPreferences sp = BKApplication.getInstance().getSharedPreferences("SP",BKApplication.MODE_PRIVATE);
        String json = sp.getString(tClass.toString(),"");
        if(!json.isEmpty()){
            Gson gson = new Gson();
            return gson.fromJson(json,tClass);
        }else {
            return null;
        }
    }
}
