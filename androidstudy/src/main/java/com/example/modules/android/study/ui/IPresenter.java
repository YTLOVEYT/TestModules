package com.example.modules.android.study.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.view.View;

/**
 * TestModules
 * Created by YinTao on 2018/12/24.
 */
public interface IPresenter<V extends IView> extends LifecycleObserver
{
    /** 绑定视图，在界面初始化完毕后调用 */
    void attachView(View view);

    /** 解绑视图，在界面销毁时调用 */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detachView();


}
