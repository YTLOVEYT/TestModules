package com.example.modules.android.study.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * ViewPagerProfileAdapter
 * Create By 樱桃 on 2019/2/18 19:25
 */
public class ViewPagerProfileAdapter extends FragmentStatePagerAdapter
{
    private List<Fragment> fragments;
    private List<String> titles;

    public ViewPagerProfileAdapter(FragmentManager fm, @NonNull List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    public ViewPagerProfileAdapter(FragmentManager fm, @NonNull List<Fragment> fragments, List<String> titles)
    {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles != null && titles.size() > 0 ? titles.get(position) : "";
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
