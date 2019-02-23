package com.example.modules.android.study.ui.fragment.profile.project;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.profile.ProjectListBean;
import com.example.modules.android.study.ui.IModel;
import com.example.modules.android.study.ui.IPresenter;
import com.example.modules.android.study.ui.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * ProjectContract
 * Create By 樱桃 on 2019/2/18 21:49
 */
public class ProjectContract
{
    interface IProjectView extends IView
    {
        void showProjectListData(ProjectListBean projectListBeans);
    }

    interface IProjectPresenter extends IPresenter<IProjectView>
    {
        void loadProjectListData(int page, int cid);
    }

    interface IProjectModel extends IModel
    {
        Observable<BaseObj<ProjectListBean>> getProjectListData(int page, int cid);
    }
}
