package com.example.modules.android.study.ui.fragment.navigation;

import com.example.modules.android.study.api.ApiService;
import com.example.modules.android.study.api.ModelService;
import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.navgation.NavigationBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * NavigationModel
 * Create By 樱桃 on 2019/2/17 16:23
 */
public class NavigationModel implements NavigationContract.INavigationModel
{
    @Override
    public Observable<BaseObj<List<NavigationBean>>> getNavigationData()
    {
        return ModelService.getRemoteData(new ModelService.MethodSelect<List<NavigationBean>>()
        {
            @Override
            public Observable<BaseObj<List<NavigationBean>>> selectMethod(ApiService service)
            {
                return service.getNavigationListData();
            }
        }, 0);
    }
}
