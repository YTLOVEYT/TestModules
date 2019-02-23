package com.example.modules.android.study.ui.fragment.profile.project;

import com.example.modules.android.study.api.ApiService;
import com.example.modules.android.study.api.ModelService;
import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.profile.ProjectListBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * ProjectModel
 * Create By 樱桃 on 2019/2/19 14:14
 */
public class ProjectModel implements ProjectContract.IProjectModel
{

    @Override
    public Observable<BaseObj<ProjectListBean>> getProjectListData(int page, int cid)
    {
        return ModelService.getRemoteData(new ModelService.MethodSelect<ProjectListBean>()
        {
            @Override
            public Observable<BaseObj<ProjectListBean>> selectMethod(ApiService service)
            {
                return service.getProjectListData(page, cid);
            }
        }, 0);
    }
}
