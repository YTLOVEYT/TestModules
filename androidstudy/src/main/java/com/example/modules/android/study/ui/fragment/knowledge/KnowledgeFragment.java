package com.example.modules.android.study.ui.fragment.knowledge;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.knowledge.KnowledgeSystemBean;
import com.example.modules.android.study.ui.fragment.BaseFragment;
import com.example.modules.base.uitls.TipsUtil;
import com.example.modules.base.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 知识体系
 * Create By YinTao On 2018/12/23 15:58
 */
public class KnowledgeFragment extends BaseFragment implements KnowledgeContract.IKnowledgeView
{

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_knowledge_welfare)
    LinearLayout llKnowledgeWelfare;
    @BindView(R.id.cv_knowledge_welfare_one)
    CircleImageView cvKnowledgeWelfareOne;
    @BindView(R.id.cv_knowledge_welfare_two)
    CircleImageView cvKnowledgeWelfareTwo;
    @BindView(R.id.ll_knowledge_list)
    LinearLayout llKnowledgeList;

    private String mCircleImg1 = "http://ww1.sinaimg.cn/large/0065oQSqly1fsb0lh7vl0j30go0ligni.jpg";
    private String mCircleImg2 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs8tym1e8ej30j60ouwhz.jpg";
    private String mCircleImg3 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs8u1joq6fj30j60orwin.jpg";
    private String mCircleImg4 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs7l8ijitfj30jg0shdkc.jpg";

    private KnowledgeContract.IKnowledgePresenter knowledgePresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

    }

    @Override
    protected int getFragmentLayoutId()
    {
        return R.layout.study_fragment_knowledge;
    }

    @Override
    protected void InitViews(View view)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("知识体系");
        InitCircleView();

    }

    /** 初始化圆形图片 */
    private void InitCircleView()
    {
        // FIXME: 2019/2/15 加载图片和设置动画

    }

    @Override
    protected void InitMVP()
    {
        if (knowledgePresenter == null)
        {
            knowledgePresenter = new KnowledgePresenter();
            getLifecycle().addObserver(knowledgePresenter);
        }
        knowledgePresenter.attachView(this);
    }

    @Override
    protected void LoadData()
    {
        knowledgePresenter.loadKnowledge();
    }

    @Override
    public void showKnowledgeSystem(List<KnowledgeSystemBean> result)
    {
        TipsUtil.logE("result=" + result);
    }

    @Override
    public Context getFragmentContext()
    {
        return getActivity();
    }
}
