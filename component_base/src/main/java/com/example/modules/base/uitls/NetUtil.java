package com.example.modules.base.uitls;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * des:网络管理工具
 * Created by xsf
 * on 2016.04.10:34
 */
public class NetUtil
{

    private NetUtil()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 检查网络是否可用
     * @param paramContext
     * @return
     */
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isNetConnected(Context paramContext)
    {
        try
        {
            NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            TipsUtil.logE("network error");
        }
        return false;
    }

    /**
     * 检测wifi是否连接
     */
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isWifiConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null)
        {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测3G是否连接
     */
    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is3gConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null)
        {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断网址是否有效
     */
    public static boolean isLinkAvailable(String link)
    {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity)
    {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}