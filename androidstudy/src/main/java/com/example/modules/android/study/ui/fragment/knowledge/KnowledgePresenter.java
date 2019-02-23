package com.example.modules.android.study.ui.fragment.knowledge;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.knowledge.KnowledgeSystemBean;
import com.example.modules.android.study.ui.mvp.BasePresenter;
import com.example.modules.android.study.ui.mvp.BaseObserver;
import com.example.modules.base.uitls.TipsUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * KnowledgePresenter
 * Create By 樱桃 on 2019/2/15 18:14
 */
public class KnowledgePresenter extends
        BasePresenter<KnowledgeContract.IKnowledgeView, KnowledgeContract.IKnowledgeModel> implements
        KnowledgeContract.IKnowledgePresenter
{

    @Override
    protected KnowledgeContract.IKnowledgeModel createModel()
    {
        return new KnowledgeModel();
    }

    @Override
    public void loadKnowledge()
    {
        addDisposable(model.loadKnowledgeData().subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<BaseObj<List<KnowledgeSystemBean>>>()
                {
                    @Override
                    public void onNext(BaseObj<List<KnowledgeSystemBean>> listBaseObj)
                    {
                        List<KnowledgeSystemBean> data = listBaseObj.getData();
                        view.showKnowledgeSystem(data);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        TipsUtil.logE("load error knowledge" + e.getMessage());
                    }

                    @Override
                    public void onComplete()
                    {
                        TipsUtil.logD("load knowledge over ");
                    }
                }));
    }


    @Override
    public void attachView(KnowledgeContract.IKnowledgeView view)
    {
        super.attachView(view);
    }


}
