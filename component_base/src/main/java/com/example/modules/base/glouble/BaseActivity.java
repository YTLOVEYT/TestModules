package com.example.modules.base.glouble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.modules.base.R;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TestModules
 * Created by YinTao on 2018/12/13.
 */
public abstract class BaseActivity extends AppCompatActivity
{

    private Unbinder unbinder;
    private ImmersionBar immersionBar;

    protected boolean isRegistEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        unbinder = ButterKnife.bind(this);
        BaseApplication.getApp().addActivity(this);
        InitImmersionBar();
        InitView();
        if (isRegistEventBus)
        {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (unbinder != null)
        {
            unbinder.unbind();
        }
        if (immersionBar != null)
        {
            immersionBar.destroy();
        }
        if (isRegistEventBus)
        {
            EventBus.getDefault().unregister(this);
        }
        BaseApplication.getApp().removeActivity(this);
    }

    private void InitImmersionBar()
    {
        immersionBar = ImmersionBar.with(this);
        immersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent()
    {

    }

    protected abstract int getContentViewId();

    protected abstract void InitView();

}
