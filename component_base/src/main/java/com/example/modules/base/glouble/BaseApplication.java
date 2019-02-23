package com.example.modules.base.glouble;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.example.modules.base.arouter.ARouterConfig;
import com.example.modules.base.uitls.AppUtil;
import com.example.modules.base.uitls.TipsUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

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
    private RefWatcher watcher;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //ARouter路由
        ARouterConfig.init(this, true);
        //Logger  日志打印
        TipsUtil.init(true, true, "----------");
        //Buggly Bug统计与热修复
        InitBugly();
        //LeakCannary 内存泄漏
        InitLeakCanary();
        //Catch 异常捕获

    }

    private void InitLeakCanary()
    {
        if (LeakCanary.isInAnalyzerProcess(getApplicationContext()))
        {
            return;
        }
        watcher = LeakCanary.install(this);
    }

    /** 用以监控Activity+Fragment */
    public RefWatcher getWatcher(Context context)
    {
        BaseApplication applicationContext = (BaseApplication) context.getApplicationContext();
        return applicationContext.watcher;
    }

    /** 初始化bugly */
    private void InitBugly()
    {
        String packageName = getApplicationContext().getPackageName();
        TipsUtil.logD("packageName=" + packageName);
        String processName = AppUtil.getAppProcessName(Process.myPid());
        TipsUtil.logD("processName=" + processName);
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), "7232e554f6", false, strategy);

    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        for (Activity activity : allActivities)
        {
            if (activity != null)
            {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        app = this;
        MultiDex.install(this);//分包
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
