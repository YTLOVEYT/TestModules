package com.example.modules.android.study.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.navgation.NavigationBean;

import java.util.List;

/**
 * NavigationTwoAdapter
 * Create By 樱桃 on 2019/2/18 15:03
 */
public class NavigationTwoAdapter extends
        BaseQuickAdapter<NavigationBean.ArticlesBean, BaseViewHolder>
{
    public NavigationTwoAdapter(@Nullable List<NavigationBean.ArticlesBean> data)
    {
        super(R.layout.study_nav_two_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean.ArticlesBean item)
    {
        if (item != null)
        {
            helper.setText(R.id.tv_nav_two, item.getTitle());
            helper.addOnClickListener(R.id.tv_nav_two);
        }
    }
}
