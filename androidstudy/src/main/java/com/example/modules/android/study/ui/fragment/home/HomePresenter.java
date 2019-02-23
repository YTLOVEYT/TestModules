package com.example.modules.android.study.ui.fragment.home;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.article.ArticleListData;
import com.example.modules.android.study.entity.banner.BannerData;
import com.example.modules.android.study.ui.mvp.BasePresenter;
import com.example.modules.android.study.ui.mvp.BaseObserver;
import com.example.modules.base.uitls.TipsUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * TestModules
 * Created by YinTao on 2018/12/24.
 */
public class HomePresenter extends
        BasePresenter<HomeContract.IHomeView, HomeContract.IHomeModel> implements
        HomeContract.IHomePresenter
{

    @Override
    public void loadBannerData()
    {
        //添加进入池管理
        TipsUtil.logE("加载banner数据。。。");
        addDisposable(model.getBannerData().subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<List<BannerData>>>()
                {
                    @Override
                    public void onNext(BaseObj<List<BannerData>> listBaseObj)
                    {
                        List<BannerData> bannerData = listBaseObj.getData();
                        TipsUtil.logE("bannerData=" + bannerData);
                        view.updateBanner(bannerData);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        TipsUtil.logE("onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete()
                    {
                        TipsUtil.logE("onComplete");
                    }
                }));
    }

    @Override
    public void loadArticleList(int page)
    {
        addDisposable(model.getArticleList(page).subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable ->
                {
                    TipsUtil.logE("doOnSubscribe");
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<ArticleListData>>(view)
                {
                    @Override
                    public void onNext(BaseObj<ArticleListData> articleListDataBaseObj)
                    {
                        ArticleListData articleListData = articleListDataBaseObj.getData();
                        view.updateListData(articleListData.getDatas());
                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                }));
    }

    @Override
    public void attachView(HomeContract.IHomeView view)
    {
        super.attachView(view);
    }


    @Override
    protected HomeContract.IHomeModel createModel()
    {
        return new HomeModel();
    }
}
