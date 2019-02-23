package com.example.modules.android.study.ui.fragment.knowledge;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.modules.android.study.R;
import com.example.modules.android.study.entity.knowledge.KnowledgeSystemBean;
import com.example.modules.android.study.ui.fragment.BaseFragment;
import com.example.modules.base.uitls.TipsUtil;
import com.example.modules.base.widget.CircleImageView;

import java.util.ArrayList;
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
    LinearLayout llKnowledgeListLayout;

    private String mCircleImg1 = "http://ww1.sinaimg.cn/large/0065oQSqly1fsb0lh7vl0j30go0ligni.jpg";
    private String mCircleImg2 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs8tym1e8ej30j60ouwhz.jpg";
    private String mCircleImg3 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs8u1joq6fj30j60orwin.jpg";
    private String mCircleImg4 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs7l8ijitfj30jg0shdkc.jpg";

    private KnowledgeContract.IKnowledgePresenter knowledgePresenter;
    private List<KnowledgeSystemBean> knowledgeSystemBeanList = new ArrayList<>();

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

    private Animation animation;

    @Override
    public void onHiddenChanged(boolean hidden)
    {

        super.onHiddenChanged(hidden);
        TipsUtil.logE("hidden2=" + hidden);
        if (hidden)         //停止
        {
            if (animation != null)
            {
                animation.cancel();
                cvKnowledgeWelfareOne.clearAnimation();
                cvKnowledgeWelfareTwo.clearAnimation();
                TipsUtil.logE("取消动画");
            }
        }
        else
        {
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.study_rotate);
            animation.setInterpolator(new LinearInterpolator());
            animation.setAnimationListener(new Animation.AnimationListener()
            {
                @Override
                public void onAnimationStart(Animation animation)
                {

                }

                @Override
                public void onAnimationEnd(Animation animation)
                {

                }

                @Override
                public void onAnimationRepeat(Animation animation)
                {

                }
            });
            cvKnowledgeWelfareOne.startAnimation(animation);
            cvKnowledgeWelfareTwo.startAnimation(animation);
        }

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
        llKnowledgeListLayout.removeAllViews();
        knowledgeSystemBeanList.clear();
        knowledgeSystemBeanList.addAll(result);
        for (int i = 0; i < knowledgeSystemBeanList.size(); i++)
        {
            View view = getLayoutInflater().inflate(R.layout.study_knowledge_item_layout, null);
            TextView tvOne = view.findViewById(R.id.tv_knowledge_item_1);
            TextView tvTwo = view.findViewById(R.id.tv_knowledge_item_2);
            ImageView imageView = view.findViewById(R.id.iv_knowledge);
            View space = view.findViewById(R.id.view_knowledge_space);
            View up = view.findViewById(R.id.view_knowledge_up);
            View down = view.findViewById(R.id.view_knowledge_down);
            if (i == 0)
            {
                space.setVisibility(View.INVISIBLE);
                up.setVisibility(View.INVISIBLE);
            }
            else if (i == knowledgeSystemBeanList.size() - 1)
            {
                down.setVisibility(View.INVISIBLE);
            }
            else
            {
                imageView.setImageResource(R.drawable.icon_circular_unselect);
            }
            tvOne.setText(knowledgeSystemBeanList.get(i).getName());
            List<KnowledgeSystemBean.ChildrenBean> children = knowledgeSystemBeanList.get(i)
                    .getChildren();
            StringBuffer buffer = new StringBuffer();
            for (KnowledgeSystemBean.ChildrenBean bean : children)
            {
                buffer.append(bean.getName() + "\n" + ";");
            }
            tvTwo.setText(Html.fromHtml("<font color='#666666'>" + buffer.toString() + "</font>"));
            int index = i;
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // FIXME: 2019/2/16
                    TipsUtil.need_toast(getActivity(), "index=" + index);
                }
            });
            llKnowledgeListLayout.addView(view);
        }
    }

    @Override
    public Context getFragmentContext()
    {
        return getActivity();
    }
}
