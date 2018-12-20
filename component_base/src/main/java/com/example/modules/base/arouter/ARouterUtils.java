package com.example.modules.base.arouter;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * TestModules
 * Created by YinTao on 2018/12/13.
 */
public class ARouterUtils
{
    public static void navigate(String path)
    {
        if (TextUtils.isEmpty(path))
        {
            return;
        }
        ARouter.getInstance().build(path).navigation();
    }

    public static void navigate(String path, Bundle bundle)
    {
        if (TextUtils.isEmpty(path))
        {
            return;
        }
        ARouter.getInstance().build(path).withBundle("bundle",bundle).navigation();
    }
}
