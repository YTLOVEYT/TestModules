package com.example.modules.android.study.custom;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import com.example.modules.base.uitls.TipsUtil;

import java.lang.reflect.Field;

/**
 * TestModules
 * Created by YinTao on 2018/12/20.
 */
public class BottomNavViewHelper
{
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view)
    {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try
        {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++)
            {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        }
        catch (NoSuchFieldException e)
        {
            TipsUtil.logE("NoSuchFieldException");
        }
        catch (IllegalAccessException e)
        {
            TipsUtil.logE("IllegalAccessException");
        }
    }
}