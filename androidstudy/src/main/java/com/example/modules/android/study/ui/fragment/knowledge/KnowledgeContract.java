package com.example.modules.android.study.ui.fragment.knowledge;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.knowledge.KnowledgeSystemBean;
import com.example.modules.android.study.mvp.interfaces.IModel;
import com.example.modules.android.study.mvp.interfaces.IPresenter;
import com.example.modules.android.study.mvp.interfaces.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * KnowledgeContract
 * Create By 樱桃 on 2019/2/15 16:47
 */
public interface KnowledgeContract
{
    interface IKnowledgeView extends IView
    {
        /**
         * 显示知识体系
         * @param result ${KnowledgeSystemBean}
         */
        void showKnowledgeSystem(List<KnowledgeSystemBean> result);
    }

    interface IKnowledgePresenter extends IPresenter<IKnowledgeView>
    {
        /** 加载知识体系 */
        void loadKnowledge();
    }

    interface IKnowledgeModel extends IModel
    {
        Observable<BaseObj<List<KnowledgeSystemBean>>> loadKnowledgeData();
    }

}
