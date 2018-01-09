package com.admin.collapsingtoolbardemo;

import android.app.Application;
import android.support.think.util.CacheUtil;
import android.support.think.util.CrashHandler;

/**
 * Created by admin on 2018/1/8.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //异常捕获（因为要捕获全局异常，所以需在这里初始化）
        CacheUtil.build(this);
        CrashHandler.build(this);
    }
}
