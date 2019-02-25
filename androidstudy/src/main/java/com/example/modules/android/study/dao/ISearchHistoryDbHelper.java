package com.example.modules.android.study.dao;

import com.example.modules.android.study.entity.search.SearchHistoryBean;

import java.util.List;

/**
 * ISearchHistoryDbHelper
 * Create By 樱桃 on 2019/2/25 16:14
 */
public interface ISearchHistoryDbHelper
{

    List<SearchHistoryBean> addHistoryData(String input);

    void clearHistoryData();

    List<SearchHistoryBean> loadAllHistoryData();

}
