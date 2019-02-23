package com.example.modules.android.study.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.navgation.NavigationBean;

import java.util.List;

/**
 * NavigationOneAdapter
 * Create By 樱桃 on 2019/2/18 14:27
 */
public class NavigationOneAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder>
{

    public NavigationOneAdapter(@Nullable List<NavigationBean> data)
    {
        super(R.layout.study_navigation_one_layout, data);
    }

    public NavigationOneAdapter(int layoutResId, @Nullable List<NavigationBean> data)
    {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item)
    {
        if (item != null)
        {
            CheckBox view = helper.getView(R.id.cb_nav_one);
            view.setChecked(item.isCheck());
            view.setText(item.getName());
            helper.setVisible(R.id.view_nav_one, item.isCheck());
            if (item.isCheck())
            {
                view.setTextColor(Color.argb(255, 244, 148, 35));
            }
            else
            {
                view.setTextColor(Color.argb(255, 205, 205, 205));
            }
            helper.addOnClickListener(R.id.cb_nav_one);
        }
    }
}
