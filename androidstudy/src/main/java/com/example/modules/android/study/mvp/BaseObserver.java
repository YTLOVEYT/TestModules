package com.example.modules.android.study.mvp;

import com.example.modules.android.study.mvp.interfaces.IView;
import com.example.modules.base.uitls.NetUtil;
import com.example.modules.base.uitls.TipsUtil;

import io.reactivex.observers.ResourceObserver;

/**
 * TestModules
 * Created by YinTao on 2018/12/25.
 */
public abstract class BaseObserver<T> extends ResourceObserver<T>
{
    private IView mView;
    private boolean isShowDialog;

    protected BaseObserver()
    {
    }

    protected BaseObserver(IView iView)
    {
        this.mView = iView;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // FIXME: 2018/12/25 添加Dialog显示
        TipsUtil.logE("BaseObserver+onStart");
        if (mView != null && mView.getFragmentContext() != null && !NetUtil.isNetConnected(mView.getFragmentContext()))
        {
            TipsUtil.must_toast(mView.getFragmentContext(), "无网络");
            onComplete();
        }
    }
}
