package com.example.modules.android.study.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

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
        if (!TextUtils.isEmpty(article.getAuthor()))
        {
            helper.setText(R.id.tv_home_item_author, article.getAuthor());
        }
        if (!TextUtils.isEmpty(article.getTitle()))
        {
            helper.setText(R.id.tv_home_item_content, Html.fromHtml(article.getTitle()));
        }
        if (!TextUtils.isEmpty(article.getChapterName()))
        {
            helper.setText(R.id.tv_home_item_title, article.getChapterName());
        }
        helper.addOnClickListener(R.id.tv_home_item_title);
        helper.addOnClickListener(R.id.tv_home_item_share);
        helper.addOnClickListener(R.id.tv_home_item_love);
    }
}
