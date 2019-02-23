package com.example.modules.android.study.ui.fragment.profile.project;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.profile.ProjectListBean;
import com.example.modules.android.study.ui.fragment.profile.ProfileContract;
import com.example.modules.android.study.ui.mvp.BaseObserver;
import com.example.modules.android.study.ui.mvp.BasePresenter;
import com.example.modules.base.uitls.TipsUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ProjectPresenter
 * Create By 樱桃 on 2019/2/19 14:12
 */
public class ProjectPresenter extends
        BasePresenter<ProjectContract.IProjectView, ProjectContract.IProjectModel> implements
        ProjectContract.IProjectPresenter
{

    @Override
    public void attachView(ProjectContract.IProjectView view)
    {
        super.attachView(view);
    }

    @Override
    protected ProjectContract.IProjectModel createModel()
    {
        return new ProjectModel();
    }

    @Override
    public void loadProjectListData(int page, int cid)
    {
        addDisposable(model.getProjectListData(page, cid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<ProjectListBean>>()
                {
                    @Override
                    public void onNext(BaseObj<ProjectListBean> projectListBeanBaseObj)
                    {
                        view.showProjectListData(projectListBeanBaseObj.getData());
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        TipsUtil.logE("project load error");
                    }

                    @Override
                    public void onComplete()
                    {
                        TipsUtil.logD("project load overs");
                    }
                }));
    }
}
