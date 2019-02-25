package com.example.modules.android.study.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.search.SearchHistoryBean;

import java.util.List;

/**
 * SearchListAdapter
 * Create By 樱桃 on 2019/2/23 17:45
 */
public class SearchListAdapter extends BaseQuickAdapter<SearchHistoryBean, BaseViewHolder>
{
    public SearchListAdapter(int layoutResId, @Nullable List<SearchHistoryBean> data)
    {
        super(R.layout.study_project_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistoryBean item)
    {

    }
}
