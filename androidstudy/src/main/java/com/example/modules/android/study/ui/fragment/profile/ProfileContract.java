package com.example.modules.android.study.ui.fragment.profile;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.profile.ProjectClassifyBean;
import com.example.modules.android.study.mvp.interfaces.IModel;
import com.example.modules.android.study.mvp.interfaces.IPresenter;
import com.example.modules.android.study.mvp.interfaces.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * ProfileContract
 * Create By 樱桃 on 2019/2/18 16:10
 */
public interface ProfileContract
{
    interface IProfileView extends IView
    {
        /**
         * 显示工程横向列表
         * @param projectClassifyBeans projectClassifyBeans
         */
        void showProjectClassifyData(List<ProjectClassifyBean> projectClassifyBeans);
    }

    interface IProfilePresenter extends IPresenter<IProfileView>
    {
        void loadProjectClassifyBean();
    }

    interface IProfileModel extends IModel
    {
        Observable<BaseObj<List<ProjectClassifyBean>>> getProjectClassifyData();
    }
}
