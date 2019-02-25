package com.example.modules.android.study.ui.fragment.home.search;

import com.example.modules.android.study.dao.green.LocalSearchDataHelper;
import com.example.modules.android.study.entity.search.SearchHistoryBean;
import com.example.modules.android.study.mvp.BasePresenter;
import com.example.modules.base.uitls.TipsUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.ISearchView, SearchContract.ISearchModel>
        implements SearchContract.ISearchPresenter
{

    @Override
    protected SearchContract.ISearchModel createModel()
    {
        return new SearchModel();
    }


    @Override
    public void saveSearchHistoryDataToDb(String input)
    {
        addDisposable(Observable.create(new ObservableOnSubscribe<List<SearchHistoryBean>>()
        {
            @Override
            public void subscribe(ObservableEmitter<List<SearchHistoryBean>> emitter) throws Exception
            {
                List<SearchHistoryBean> historyBeans = LocalSearchDataHelper.getInstance().addHistoryData(input);
                emitter.onNext(historyBeans);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SearchHistoryBean>>()
                {
                    @Override
                    public void accept(List<SearchHistoryBean> historyBeans) throws Exception
                    {
                        TipsUtil.logE("insert success");
                    }
                }));
    }

    @Override
    public void loadSearchHistoryDataFromDb()
    {
        addDisposable(Observable.create(new ObservableOnSubscribe<List<SearchHistoryBean>>()
        {
            @Override
            public void subscribe(ObservableEmitter<List<SearchHistoryBean>> emitter) throws Exception
            {
                List<SearchHistoryBean> historyBeans = LocalSearchDataHelper.getInstance().loadAllHistoryData();
                emitter.onNext(historyBeans);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SearchHistoryBean>>()
                {
                    @Override
                    public void accept(List<SearchHistoryBean> historyBeans) throws Exception
                    {
                        view.showSearchHistoryData(historyBeans);
                    }
                }));
    }

    @Override
    public void loadHotSearchData()
    {

    }

    @Override
    public void attachView(SearchContract.ISearchView view)
    {
        super.attachView(view);
    }
}
