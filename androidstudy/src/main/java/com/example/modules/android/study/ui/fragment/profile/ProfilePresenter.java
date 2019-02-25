package com.example.modules.android.study.ui.fragment.profile;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.profile.ProjectClassifyBean;
import com.example.modules.android.study.mvp.BaseObserver;
import com.example.modules.android.study.mvp.BasePresenter;
import com.example.modules.base.uitls.TipsUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ProfilePresenter
 * Create By 樱桃 on 2019/2/18 16:46
 */
public class ProfilePresenter extends
        BasePresenter<ProfileContract.IProfileView, ProfileContract.IProfileModel> implements
        ProfileContract.IProfilePresenter
{
    @Override
    public void loadProjectClassifyBean()
    {
        addDisposable(model.getProjectClassifyData()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<List<ProjectClassifyBean>>>()
                {
                    @Override
                    public void onNext(BaseObj<List<ProjectClassifyBean>> listBaseObj)
                    {
                        List<ProjectClassifyBean> data = listBaseObj.getData();
                        view.showProjectClassifyData(data);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        TipsUtil.logE("profile_one load error=" + e.getMessage());
                    }

                    @Override
                    public void onComplete()
                    {
                        TipsUtil.logD("profile load over");
                    }
                }));
    }

    @Override
    public void attachView(ProfileContract.IProfileView view)
    {
        super.attachView(view);
    }

    @Override
    protected ProfileContract.IProfileModel createModel()
    {
        return new ProfileModel();
    }
}
