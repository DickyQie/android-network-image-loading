package com.zq.frescodemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zq on 2017/1/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Fresco
        Fresco.initialize(getApplicationContext());
    }
}
