package com.example.modules.android.study.ui.fragment.knowledge;

import com.example.modules.android.study.api.ApiService;
import com.example.modules.android.study.api.ModelService;
import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.knowledge.KnowledgeSystemBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * KnowledgeModel
 * Create By 樱桃 on 2019/2/15 18:43
 */
public class KnowledgeModel implements KnowledgeContract.IKnowledgeModel
{

    @Override
    public Observable<BaseObj<List<KnowledgeSystemBean>>> loadKnowledgeData()
    {
        return ModelService.getRemoteData(new ModelService.MethodSelect<List<KnowledgeSystemBean>>()
        {
            @Override
            public Observable<BaseObj<List<KnowledgeSystemBean>>> selectMethod(ApiService service)
            {
                return service.getKnowledgeSystemData();
            }
        }, 0);
    }
}
