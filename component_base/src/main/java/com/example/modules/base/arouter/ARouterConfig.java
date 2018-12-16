package com.example.modules.base.arouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * TestModules
 * Created by YinTao on 2018/12/13.
 */
public class ARouterConfig
{
    public static  void init(Application application,boolean isDebug)
    {
        if (isDebug)
        {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }
}
