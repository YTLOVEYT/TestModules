package com.example.modules.android.study.ui.fragment.profile.project;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.modules.android.study.R;
import com.example.modules.android.study.adapter.ProjectListAdapter;
import com.example.modules.android.study.entity.profile.ProjectListBean;
import com.example.modules.android.study.ui.fragment.BaseFragment;
import com.example.modules.base.uitls.TipsUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ProjectFragment
 * Create By 樱桃 on 2019/2/18 16:58
 */
public class ProjectFragment extends BaseFragment implements ProjectContract.IProjectView
{
    @BindView(R.id.rv_profile_project)
    RecyclerView rvProfileProject;
    @BindView(R.id.srl_profile_project)
    SmartRefreshLayout srlProfileProject;
    private int cid;
    private ArrayList<ProjectListBean.DatasBean> data;
    private ProjectListAdapter projectListAdapter;
    private ProjectContract.IProjectPresenter projectPresenter;
    private static int page = 0;
    private boolean isRefresh = false;

    public static ProjectFragment getInstance(int params1, String params2)
    {
        ProjectFragment projectFragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("params1", params1);
        bundle.putString("params2", params2);
        projectFragment.setArguments(bundle);
        return projectFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutId()
    {
        return R.layout.study_fragment_project;
    }

    @Override
    protected void InitViews(View view)
    {
        srlProfileProject.setOnRefreshListener(new OnRefreshListener()
        {
            @Override
            public void onRefresh(RefreshLayout refreshLayout)
            {
                TipsUtil.logD("refreshing=" + page);
                page = 0;
                isRefresh = true;
                projectPresenter.loadProjectListData(page, cid);
            }
        });
        srlProfileProject.setOnLoadMoreListener(new OnLoadMoreListener()
        {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout)
            {
                page++;
                TipsUtil.logD("loading=" + page);
                isRefresh = false;
                projectPresenter.loadProjectListData(page, cid);
            }
        });
        srlProfileProject.autoRefresh();
        Bundle bundle = getArguments();
        if (bundle != null)
        {
            cid = bundle.getInt("params1");
        }
        TipsUtil.logE("cid=" + cid);
        rvProfileProject.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProfileProject.setHasFixedSize(true);
        data = new ArrayList<>();
        projectListAdapter = new ProjectListAdapter(data);
        rvProfileProject.setAdapter(projectListAdapter);
        projectListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                TipsUtil.need_toast(getActivity(), "position=" + position);
            }
        });
        projectListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                TipsUtil.need_toast(getActivity(), "position1=" + position);
            }
        });
    }

    @Override
    protected void InitMVP()
    {
        if (projectPresenter == null)
        {
            projectPresenter = new ProjectPresenter();
        }
        projectPresenter.attachView(this);
    }

    @Override
    protected void LoadData()
    {
        projectPresenter.loadProjectListData(page, cid);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void showProjectListData(ProjectListBean projectListBeans)
    {
        TipsUtil.logE("projectListBeans=" + projectListBeans);
        if (isRefresh)
        {
            projectListAdapter.replaceData(projectListBeans.getDatas());
        }
        else
        {
            if (projectListBeans.getDatas().size() > 0)
            {
                projectListAdapter.addData(projectListBeans.getDatas());
            }
        }
        if (srlProfileProject != null)
        {
            srlProfileProject.finishRefresh();
            srlProfileProject.finishLoadMore();
        }
    }

    @Override
    public Context getFragmentContext()
    {
        return getActivity();
    }
}
