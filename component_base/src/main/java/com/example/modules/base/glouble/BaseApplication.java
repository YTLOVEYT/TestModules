package com.example.modules.base.glouble;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.example.modules.base.arouter.ARouterConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * TestModules
 * Created by YinTao on 2018/12/12.
 */
public class BaseApplication extends Application
{
    /** 统一管理Activity */
    private Set<Activity> allActivities = new HashSet<>();


    private static BaseApplication app;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //ARouter路由
        ARouterConfig.init(this, true);
        //Buggly Bug统计与热修复

        //LeakCannary 内存泄漏

        //Logger  日志打印

        //Catch 异常捕获

    }


    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        app = this;
        MultiDex.install(this);
    }

    public static BaseApplication getApp()
    {
        return app;
    }

    /** 添加Activity */
    public void addActivity(@NonNull Activity activity)
    {
        if (allActivities == null)
        {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    /** 删除Activity */
    public void removeActivity(@NonNull Activity activity)
    {
        if (allActivities != null)
        {
            allActivities.remove(activity);
        }
    }
}
