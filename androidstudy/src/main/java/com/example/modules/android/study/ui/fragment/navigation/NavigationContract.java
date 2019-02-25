package com.example.modules.android.study.ui.fragment.navigation;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.navgation.NavigationBean;
import com.example.modules.android.study.mvp.interfaces.IModel;
import com.example.modules.android.study.mvp.interfaces.IPresenter;
import com.example.modules.android.study.mvp.interfaces.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * NavigationContract
 * Create By 樱桃 on 2019/2/17 16:11
 */
public interface NavigationContract
{
    interface INavigationView extends IView
    {
        /**
         * 显示导航列表
         * @param navigationBeans List
         */
        void showNavigationList(List<NavigationBean> navigationBeans);
    }

    interface INavigationPresenter extends IPresenter<INavigationView>
    {
        void loadNavigationItems();
    }

    interface INavigationModel extends IModel
    {
        Observable<BaseObj<List<NavigationBean>>> getNavigationData();
    }

}
