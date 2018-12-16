package com.example.modules.two.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.modules.base.arouter.ARouterUrls;
import com.example.modules.base.glouble.BaseActivity;
import com.example.modules.two.R;

@Route(path = ARouterUrls.PATH_MODULE_TWO)
public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.two_activity_main;
    }

    @Override
    protected void InitView()
    {

    }
}
