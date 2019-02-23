package com.example.modules.base.uitls;

import android.content.Context;

/**
 * CommonUtil
 * Create By 樱桃 on 2019/2/17 14:57
 */
public class CommonUtil
{
    /**
     * dp转px
     * @param context context
     * @param dp      dp
     * @return px
     */
    public static int dp2px(Context context, int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    /**
     * px转dp
     * @param context context
     * @param px      px
     * @return dp
     */
    public static int px2dp(Context context, int px)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }
}
