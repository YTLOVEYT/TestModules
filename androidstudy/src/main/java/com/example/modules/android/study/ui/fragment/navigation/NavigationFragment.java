package com.example.modules.android.study.ui.fragment.navigation;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.modules.android.study.R;
import com.example.modules.android.study.adapter.NavigationOneAdapter;
import com.example.modules.android.study.adapter.NavigationTwoAdapter;
import com.example.modules.android.study.entity.navgation.NavigationBean;
import com.example.modules.android.study.ui.fragment.BaseFragment;
import com.example.modules.base.uitls.TipsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 导航
 * Create By YinTao On 2018/12/23 16:00
 */
public class NavigationFragment extends BaseFragment implements NavigationContract.INavigationView,
        BaseQuickAdapter.OnItemChildClickListener
{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_nav_one)
    RecyclerView rvNavOne;
    @BindView(R.id.rv_nav_two)
    RecyclerView rvNavTwo;
    private List<NavigationBean> navigationBeanList;
    private List<NavigationBean.ArticlesBean> navigationBeansArticleBeans;

    private NavigationContract.INavigationPresenter navigationPresenter;
    private NavigationOneAdapter navigationoneAdapter;
    private NavigationTwoAdapter navigationTwoAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutId()
    {
        return R.layout.study_fragment_navigation;
    }

    @Override
    protected void InitViews(View view)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("导航");
        navigationBeanList = new ArrayList<>();
        navigationBeansArticleBeans = new ArrayList<>();
        rvNavOne.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNavTwo.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    protected void InitMVP()
    {
        if (navigationPresenter == null)
        {
            navigationPresenter = new NavigationPresenter();
        }
        navigationPresenter.attachView(this);
    }

    @Override
    protected void LoadData()
    {
        navigationPresenter.loadNavigationItems();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void showNavigationList(List<NavigationBean> navigationBeans)
    {
        TipsUtil.logE("navigationBeans=" + navigationBeans);
        if (navigationBeans != null && navigationBeans.size() > 0)
        {
            navigationBeanList = navigationBeans;
            navigationBeanList.get(0).setCheck(true);
            navigationoneAdapter = new NavigationOneAdapter(navigationBeanList);
            navigationoneAdapter.setOnItemChildClickListener(this);
            navigationoneAdapter.openLoadAnimation(BaseQuickAdapter.FOOTER_VIEW);
            rvNavOne.setAdapter(navigationoneAdapter);

            //二级目录
            navigationBeansArticleBeans = navigationBeanList.get(0).getArticles();
            navigationTwoAdapter = new NavigationTwoAdapter(navigationBeansArticleBeans);
            navigationTwoAdapter.setOnItemChildClickListener(this);
            rvNavTwo.setAdapter(navigationTwoAdapter);
        }
    }

    @Override
    public Context getFragmentContext()
    {
        return getActivity();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
    {
        if (view.getId() == R.id.cb_nav_one)
        {
            TipsUtil.need_toast(getActivity(), "position=" + position);
            for (NavigationBean bean : navigationBeanList)
            {
                bean.setCheck(false);
            }
            navigationBeanList.get(position).setCheck(true);
            navigationoneAdapter.notifyDataSetChanged();

            navigationBeansArticleBeans = navigationBeanList.get(position).getArticles();
            navigationTwoAdapter = new NavigationTwoAdapter(navigationBeansArticleBeans);
            navigationTwoAdapter.setOnItemChildClickListener(this);
            rvNavTwo.setAdapter(navigationTwoAdapter);
        }
        else if (view.getId() == R.id.tv_nav_two)
        {
            TipsUtil.need_toast(getActivity(), "click=" + ((TextView) view).getText().toString());
        }
    }
}
