package com.example.modules.android.study.ui.fragment;

import android.support.annotation.CallSuper;

import com.example.modules.android.study.ui.IModel;
import com.example.modules.android.study.ui.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BasePresenter
 * Create By 樱桃 on 2019/2/15 17:33
 */
public abstract class BasePresenter<V extends IView, M extends IModel>
{
    protected V view;
    protected M model;

    private CompositeDisposable compositeDisposable;

    @CallSuper //强调子类在覆盖父类的方法时需要实现父类的方法
    protected void attachView(V view)
    {
        this.view = view;
        if (model == null)
        {
            model = createModel();
        }
    }

    @CallSuper
    public void detachView()
    {
        if (model != null)
        {
            clearDisposablePool();
        }
        model = null;
        view = null;
    }

    /**
     * 管理RxJava事物请求
     * @param disposable 一对
     */
    protected void addDisposable(Disposable disposable)
    {
        if (compositeDisposable == null)
        {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /** 清除所有请求 */
    protected void clearDisposablePool()
    {
        if (compositeDisposable != null)
        {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    protected abstract M createModel();

}
