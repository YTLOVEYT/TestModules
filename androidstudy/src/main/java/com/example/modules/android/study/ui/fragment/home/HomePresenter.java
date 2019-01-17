package com.example.modules.android.study.ui.fragment.home;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.article.ArticleListData;
import com.example.modules.android.study.entity.banner.BannerData;
import com.example.modules.android.study.ui.mvp.BaseObserver;
import com.example.modules.base.uitls.TipsUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * TestModules
 * Created by YinTao on 2018/12/24.
 */
public class HomePresenter implements HomeContract.IHomePresenter
{
    private HomeContract.IHomeView mView;
    private HomeContract.IHomeModel mModel;
    private CompositeDisposable compositeDisposable;//管理观察者与被观察者的关联事件

    @Override
    public void loadBannerData()
    {
        //添加进入池管理
        TipsUtil.logE("加载banner数据。。。");
        addDisposable(mModel.getBannerData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<List<BannerData>>>()
                {
                    @Override
                    public void onNext(BaseObj<List<BannerData>> listBaseObj)
                    {
                        List<BannerData> bannerData = listBaseObj.getData();
                        TipsUtil.logE("bannerData=" + bannerData);
                        mView.updateBanner(bannerData);
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
        addDisposable(mModel.getArticleList(page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable ->
                {
                    TipsUtil.logE("doOnSubscribe");
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<ArticleListData>>(mView)
                {
                    @Override
                    public void onNext(BaseObj<ArticleListData> articleListDataBaseObj)
                    {
                        ArticleListData articleListData = articleListDataBaseObj.getData();
                        mView.updateListData(articleListData.getDatas());
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
        this.mView = view;
        if (mModel == null)
        {
            mModel = new HomeModel();
        }
    }

    @Override
    public void detachView()
    {
        if (mModel != null)
        {
            clearDisposable();
        }
        mView = null;
        mModel = null;
    }

    /** Presenter添加观察者事件 */
    protected void addDisposable(Disposable disposable)
    {
        if (compositeDisposable == null)
        {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /** Presenter卸载当前观察者事件 */
    protected void clearDisposable()
    {
        if (compositeDisposable != null)
        {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
