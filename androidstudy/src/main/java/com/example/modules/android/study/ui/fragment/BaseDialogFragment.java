package com.example.modules.android.study.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modules.base.event.BaseEvent;
import com.example.modules.base.glouble.BaseApplication;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TestModules
 * Created by YinTao on 2018/12/23.
 */
public abstract class BaseDialogFragment extends DialogFragment
{
    private Unbinder bind;
    private Context context;
    protected boolean isRegEventBus = false;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(getFragmentLayoutId(), container, false); //视图
        bind = ButterKnife.bind(this, view); //绑定控件
        InitViews(view);        //初始化视图
        InitMVP();
        LoadData();
        if (isRegEventBus)
        {
            EventBus.getDefault().register(this);
        }

        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        if (bind != null)
        {
            bind.unbind();
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (isRegEventBus)
        {
            EventBus.getDefault().unregister(this);
        }
        RefWatcher watcher = BaseApplication.getApp().getWatcher(context);
        watcher.watch(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(BaseEvent event)
    {

    }


    protected abstract int getFragmentLayoutId();

    protected abstract void InitViews(View view);

    protected abstract void InitMVP();

    protected abstract void LoadData();
}
