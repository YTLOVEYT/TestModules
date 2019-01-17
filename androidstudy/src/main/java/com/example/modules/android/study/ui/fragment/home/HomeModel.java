package com.example.modules.android.study.ui.fragment.home;

import com.example.modules.android.study.api.ApiService;
import com.example.modules.android.study.api.ModelService;
import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.article.ArticleListData;
import com.example.modules.android.study.entity.banner.BannerData;

import java.util.List;

import io.reactivex.Observable;

/**
 * HomeModel（{M}VP最底层）
 * 负责具体的业务数据请求
 * Created by YinTao on 2018/12/24.
 */
public class HomeModel implements HomeContract.IHomeModel
{
    public HomeModel()
    {

    }

    @Override
    public Observable<BaseObj<ArticleListData>> getArticleList(int page)
    {
        return ModelService.getRemoteData(new ModelService.MethodSelect<ArticleListData>()
        {
            @Override
            public Observable<BaseObj<ArticleListData>> selectMethod(ApiService service)
            {
                return service.getArticleListData(page);
            }
        });
    }

    @Override
    public Observable<BaseObj<List<BannerData>>> getBannerData()
    {
        return ModelService.getRemoteData(new ModelService.MethodSelect<List<BannerData>>()
        {
            @Override
            public Observable<BaseObj<List<BannerData>>> selectMethod(ApiService service)
            {
                return service.getBannerData();
            }
        });
    }
}
