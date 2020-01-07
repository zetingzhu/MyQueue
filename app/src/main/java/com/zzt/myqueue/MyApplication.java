package com.zzt.myqueue;

import android.app.Application;
import android.content.Context;

/**
 * @author: zeting
 * @date: 2019/12/28
 */
public class MyApplication extends Application {
    private static  MyApplication ourInstance  ;

    public static MyApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this ;

    }


}
