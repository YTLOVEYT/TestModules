package com.example.modules.three.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.modules.base.arouter.ARouterUrls;
import com.example.modules.base.glouble.BaseActivity;
import com.example.modules.three.R;

@Route(path = ARouterUrls.PATH_MODULE_THREE)
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
        return R.layout.three_activity_main;
    }

    @Override
    protected void InitView()
    {

    }
}
