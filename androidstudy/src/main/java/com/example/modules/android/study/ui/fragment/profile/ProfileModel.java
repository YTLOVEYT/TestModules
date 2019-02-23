package com.example.modules.android.study.ui.fragment.profile;

import com.example.modules.android.study.api.ApiService;
import com.example.modules.android.study.api.ModelService;
import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.profile.ProjectClassifyBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * ProfileModel
 * Create By 樱桃 on 2019/2/18 16:47
 */
public class ProfileModel implements ProfileContract.IProfileModel
{
    @Override
    public Observable<BaseObj<List<ProjectClassifyBean>>> getProjectClassifyData()
    {
        return ModelService.getRemoteData(new ModelService.MethodSelect<List<ProjectClassifyBean>>()
        {
            @Override
            public Observable<BaseObj<List<ProjectClassifyBean>>> selectMethod(ApiService service)
            {
                return service.getProjectClassifyData();
            }
        }, 0);
    }
}
