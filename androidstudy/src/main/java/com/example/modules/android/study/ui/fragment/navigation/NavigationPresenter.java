package com.example.modules.android.study.ui.fragment.navigation;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.navgation.NavigationBean;
import com.example.modules.android.study.ui.mvp.BaseObserver;
import com.example.modules.android.study.ui.mvp.BasePresenter;
import com.example.modules.base.uitls.TipsUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * NavigationPresenter
 * Create By 樱桃 on 2019/2/17 16:20
 */
public class NavigationPresenter extends
        BasePresenter<NavigationContract.INavigationView, NavigationContract.INavigationModel> implements
        NavigationContract.INavigationPresenter
{
    @Override
    public void loadNavigationItems()
    {
        addDisposable(model.getNavigationData().subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<List<NavigationBean>>>()
                {
                    @Override
                    public void onNext(BaseObj<List<NavigationBean>> listBaseObj)
                    {
                        List<NavigationBean> navigationBeans = listBaseObj.getData();
                        view.showNavigationList(navigationBeans);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        TipsUtil.logE("load navigation error" + e.getMessage());
                    }

                    @Override
                    public void onComplete()
                    {
                        TipsUtil.logD("navigation load over");
                    }
                }));
    }

    @Override
    public void attachView(NavigationContract.INavigationView view)
    {
        super.attachView(view);
    }

    @Override
    protected NavigationContract.INavigationModel createModel()
    {
        return new NavigationModel();
    }
}
