package com.example.modules.android.study.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.profile.ProjectListBean;
import com.example.modules.android.study.helper.ImageLoaderHelper;

import java.util.List;

/**
 * ProjectListAdapter
 * Create By 樱桃 on 2019/2/18 19:51
 */
public class ProjectListAdapter extends BaseQuickAdapter<ProjectListBean.DatasBean, BaseViewHolder>
{
    public ProjectListAdapter(@Nullable List<ProjectListBean.DatasBean> data)
    {
        super(R.layout.study_project_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListBean.DatasBean item)
    {
        if (!TextUtils.isEmpty(item.getEnvelopePic()))
        {
            ImageLoaderHelper.getInstance()
                    .load(mContext, item.getEnvelopePic(), (ImageView) helper.getView(R.id.iv_project_item));
        }
        if (!TextUtils.isEmpty(item.getTitle()))
        {
            helper.setText(R.id.tv_project_title, item.getTitle());
        }

        if (!TextUtils.isEmpty(item.getDesc()))
        {
            helper.setText(R.id.tv_project_item, item.getDesc());
        }
        if (!TextUtils.isEmpty(item.getNiceDate()))
        {
            helper.setText(R.id.tv_project_date, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getAuthor()))
        {
            helper.setText(R.id.tv_project_author, item.getAuthor());
        }
        helper.addOnClickListener(R.id.tv_project_title);
    }
}
