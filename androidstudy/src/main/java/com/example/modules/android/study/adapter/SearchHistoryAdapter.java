package com.example.modules.android.study.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.search.SearchHistoryBean;

import java.util.List;

/**
 * SearchHistoryAdapter
 * Create By 樱桃 on 2019/2/25 18:34
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<SearchHistoryBean, BaseViewHolder>
{
    public SearchHistoryAdapter(@Nullable List<SearchHistoryBean> data)
    {
        super(R.layout.study_search_history_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistoryBean item)
    {
        helper.setText(R.id.tv_search_history, item.getKeyword());
    }
}
