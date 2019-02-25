package com.example.modules.android.study.ui.fragment.home.search;

import com.example.modules.android.study.entity.search.HotSearchBean;
import com.example.modules.android.study.entity.search.SearchHistoryBean;
import com.example.modules.android.study.mvp.interfaces.IModel;
import com.example.modules.android.study.mvp.interfaces.IPresenter;
import com.example.modules.android.study.mvp.interfaces.IView;

import java.util.List;


public interface SearchContract
{
    /**
     * ISearchView---M{V}P
     */
    interface ISearchView extends IView
    {
        /**
         * 显示历史搜索数据
         * @param historyBeans ${SearchHistoryBean}
         */
        void showSearchHistoryData(List<SearchHistoryBean> historyBeans);

        /**
         * 显示热搜数据
         * @param searchBeans ${HotSearchBean}
         */
        void showHotSearchData(List<HotSearchBean> searchBeans);

    }

    /**
     * ISearchPresenter---MV{P}
     */
    interface ISearchPresenter extends IPresenter<ISearchView>
    {
        void saveSearchHistoryDataToDb(String input);

        void loadSearchHistoryDataFromDb();

        void loadHotSearchData();
    }

    /**
     * ISearchModel---{M}VP
     */
    interface ISearchModel extends IModel
    {

    }
}
