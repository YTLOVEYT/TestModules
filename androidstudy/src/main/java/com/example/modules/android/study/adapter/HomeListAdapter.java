package com.example.modules.android.study.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.article.ArticleData;

import java.util.List;

/**
 * HomeListAdapter
 * 首页列表的适配器
 * Created by YinTao on 2018/12/25.
 */
public class HomeListAdapter extends BaseQuickAdapter<ArticleData, BaseViewHolder>
{

    public HomeListAdapter(@Nullable List<ArticleData> data)
    {
        super(R.layout.study_item_recycleview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleData article)
    {

    }
}
