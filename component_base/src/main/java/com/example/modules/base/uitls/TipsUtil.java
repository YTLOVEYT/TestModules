package com.example.modules.base.uitls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Hello
 * Created by YinTao on 2018/11/16.
 */
@SuppressLint("ShowToast")
public class TipsUtil
{
    private static boolean isShowToast = true;
    private static boolean isShowLog = true;
    private static Toast toast;
    private static String TAG = "-------------------";

    private TipsUtil()
    {

    }

    public static void init(boolean showToast, boolean showLog, String tag)
    {
        isShowToast = showToast;
        isShowLog = showLog;
        TAG = tag;
    }

    public static void must_toast(Context context, String msg)
    {
        if (toast == null)
        {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }


    public static void need_toast(Context context, String msg)
    {
        if (!isShowToast) return;
        if (toast == null)
        {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    public static void logE(String msg)
    {
        if (!isShowLog) return;
        Log.e(TAG, msg);
    }

    public static void logD(String msg)
    {
        if (!isShowLog) return;
        Log.d(TAG, msg);
    }

}
