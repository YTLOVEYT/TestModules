package com.example.modules.android.study.ui.fragment.knowledge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modules.android.study.R;

/**
 * 知识体系
 * Create By YinTao On 2018/12/23 15:58
 */
public class KnowledgeFragment extends Fragment
{
    public KnowledgeFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.study_fragment_knowledge, container, false);

        return view;
    }

}
