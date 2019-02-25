package com.example.modules.android.study.ui.fragment.home;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.article.ArticleData;
import com.example.modules.android.study.entity.article.ArticleListData;
import com.example.modules.android.study.entity.banner.BannerData;
import com.example.modules.android.study.mvp.interfaces.IModel;
import com.example.modules.android.study.mvp.interfaces.IPresenter;
import com.example.modules.android.study.mvp.interfaces.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * HomeContract
 * 负责MVP的任务接口
 * Created by YinTao on 2018/12/24.
 */
public interface HomeContract
{
    /**
     * IHomeView---M{V}P
     */
    interface IHomeView extends IView
    {
        void updateBanner(List<BannerData> data);

        void updateListData(List<ArticleData> articleData);
    }

    /**
     * IHomePresenter---MV{P}
     */
    interface IHomePresenter extends IPresenter<IHomeView>
    {
        /** 加载banner数据 */
        void loadBannerData();

        /**
         * 加载文章集合数据
         * @param page 页数
         */
        void loadArticleList(int page);

    }

    /**
     * IHomeModel---{M}VP
     */
    interface IHomeModel extends IModel
    {
        /**
         * 获取文章列表集合的被观察者
         * @param page 页数
         * @return Observable被观察者
         */
        Observable<BaseObj<ArticleListData>> getArticleList(int page);

        /**
         * 获取滚屏数据集合
         * @return 数据集合
         */
        Observable<BaseObj<List<BannerData>>> getBannerData();
    }


}
