package com.example.modules.base.uitls;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

/**
 * AppUtil
 * Create By 樱桃 on 2019/2/21 16:56
 */
public class AppUtil
{
    public static String getAppProcessName(int pid)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("/proc" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName))
            {
                return processName.trim();
            }
        }
        catch (Exception e)
        {
            TipsUtil.logE("1.get app process name failed" + e.getMessage());
        }
        return null;
    }

    public static String getProcessName(Context context, int pid)
    {
        try
        {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager == null)
            {
                return null;
            }
            List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : processes)
            {
                if (processInfo.pid == pid)
                {
                    return processInfo.processName;
                }
            }
        }
        catch (Exception e)
        {
            TipsUtil.logE("2.get app process name failed" + e.getMessage());
        }
        return null;
    }
}
