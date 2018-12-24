package com.example.modules.android.study.ui.fragment.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modules.android.study.R;

/**
 * 导航
 * Create By YinTao On 2018/12/23 16:00
 */
public class NavigationFragment extends Fragment
{


    public NavigationFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.study_fragment_navigation, container, false);

        return view;
    }

}
