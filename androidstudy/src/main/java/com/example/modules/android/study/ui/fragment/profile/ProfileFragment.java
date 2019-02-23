package com.example.modules.android.study.ui.fragment.profile;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.modules.android.study.R;
import com.example.modules.android.study.adapter.ViewPagerProfileAdapter;
import com.example.modules.android.study.entity.profile.ProjectClassifyBean;
import com.example.modules.android.study.ui.fragment.BaseFragment;
import com.example.modules.android.study.ui.fragment.profile.project.ProjectFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人主页
 * Create By YinTao On 2018/12/23 16:01
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.IProfileView
{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.st_profile)
    SlidingTabLayout stProfile;
    @BindView(R.id.vp_profile_project)
    ViewPager vpProfileProject;

    private List<ProjectClassifyBean> projectClassifyBeansList;
    private ProfileContract.IProfilePresenter profilePresenter;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutId()
    {
        return R.layout.study_fragment_profile;
    }

    @Override
    protected void InitViews(View view)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("项目");

    }

    @Override
    protected void InitMVP()
    {
        if (profilePresenter == null)
        {
            profilePresenter = new ProfilePresenter();
        }
        profilePresenter.attachView(this);
    }

    @Override
    protected void LoadData()
    {
        profilePresenter.loadProjectClassifyBean();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyBean> projectClassifyBeans)
    {
        if (projectClassifyBeans != null)
        {
            projectClassifyBeansList = projectClassifyBeans;
            for (ProjectClassifyBean bean : projectClassifyBeansList)
            {
                ProjectFragment fragment = ProjectFragment.getInstance(bean.getId(), null);
                fragments.add(fragment);
                titleList.add(bean.getName());
            }
            vpProfileProject.setAdapter(new ViewPagerProfileAdapter(getFragmentManager(), fragments, titleList));
            vpProfileProject.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
            {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
                {

                }

                @Override
                public void onPageSelected(int position)
                {

                }

                @Override
                public void onPageScrollStateChanged(int state)
                {

                }
            });
            stProfile.setViewPager(vpProfileProject);
            vpProfileProject.setCurrentItem(0);
        }
    }

    @Override
    public Context getFragmentContext()
    {
        return getActivity();
    }
}
