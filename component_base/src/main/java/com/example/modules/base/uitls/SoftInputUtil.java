package com.example.modules.base.uitls;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * SoftInputUtil
 * Create By 樱桃 on 2019/2/23 17:07
 */
public class SoftInputUtil
{
    private SoftInputUtil()
    {
        throw new UnsupportedOperationException("error");
    }

    /**
     * 显示软件盘输入
     * @param context  context
     * @param editText editText
     */
    public static void openSoftInputKeyboard(Context context, EditText editText)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
        {
            inputMethodManager.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * 隐藏软键盘
     * @param context  context
     * @param editText editText
     */
    public static void closeSoftInputKeyboard(Context context, EditText editText)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
        {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}