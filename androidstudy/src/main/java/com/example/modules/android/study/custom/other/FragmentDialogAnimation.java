package com.example.modules.android.study.custom.other;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import com.example.modules.base.uitls.TipsUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * FragmentDialogAnimation
 * Create By 樱桃 on 2019/2/23 20:27
 */
public class FragmentDialogAnimation
{
    private static final int DURATION_TIME = 1000;
    private MyAnimListener myAnimListener;
    private Animator animator;

    /**
     * 动画内部使用
     * @param isShow      是否显示动画
     * @param triggerView 触发视图
     * @param animView
     */
    @SuppressLint("NewApi")
    private void actionOtherVisible(boolean isShow, View triggerView, View animView)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) //API 21
        {
            if (isShow)
            {
                animView.setVisibility(View.VISIBLE);

            }
            else
            {
                animView.setVisibility(View.GONE);
            }
            return;
        }
        int[] tvLocation = {0, 0};
        triggerView.getLocationInWindow(tvLocation);//获取触发的window中的位置
        TipsUtil.logD("tvLocation=(" + tvLocation[0] + "," + tvLocation[1] + ")");//(135,51)
        int tvX = tvLocation[0] + triggerView.getWidth() / 2;//触发view的中心
        int tvY = tvLocation[1] + triggerView.getHeight() / 2;
        TipsUtil.logD("(tvX,tvY)=(" + tvX + "," + tvY + ")");

        int[] avLocation = {0, 0};
        animView.getLocationInWindow(avLocation);//获取动画的window中的位置
        TipsUtil.logD("avLocation=(" + avLocation[0] + "," + avLocation[1] + ")");
        int avX = avLocation[0] + animView.getWidth() / 2;
        int avY = avLocation[1] + animView.getHeight() / 2;//根view的中心（1080/2，1920/2）
        TipsUtil.logD("(avX,avY)=(" + avX + "," + avY + ")");

        int rippleW, rippleH;
        if (tvX < avX)
        {
            rippleW = animView.getWidth() - tvX;//1080-512
        }
        else
        {
            rippleW = tvX - avLocation[0];//512-0
        }
        if (tvY < avY)
        {
            rippleH = animView.getHeight() - tvY;
        }
        else
        {
            rippleH = tvY - avLocation[1];//72-0
        }
        TipsUtil.logD("(rippleW,rippleH)=(" + rippleW + "," + rippleH + ")");
        float maxRadius = (float) Math.sqrt(rippleW * rippleW + rippleH * rippleH);
        float startRadius, endRadius;
        if (isShow)
        {
            startRadius = 0f;
            endRadius = maxRadius;
        }
        else
        {
            startRadius = maxRadius;
            endRadius = 0f;
        }
        TipsUtil.logD("(startRadius,endRadius)=(" + startRadius + "," + endRadius + ")");
        animator = ViewAnimationUtils.createCircularReveal(animView, tvX, tvY, startRadius, endRadius);
        animView.setVisibility(View.VISIBLE);
        animator.setDuration(DURATION_TIME);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                if (isShow)
                {
                    animView.setVisibility(View.VISIBLE);
                    if (myAnimListener != null)
                    {
                        myAnimListener.onAnimationShowEnd();//显示完毕
                    }
                }
                else
                {
                    animView.setVisibility(View.GONE);
                    if (myAnimListener != null)
                    {
                        myAnimListener.onAnimationHideEnd();//隐藏完毕
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });
        animator.start();
    }

    public interface MyAnimListener
    {
        void onAnimationShowEnd();

        void onAnimationHideEnd();
    }

    public void showAnimation(View triggerView, View showView)
    {
        actionOtherVisible(true, triggerView, showView);
    }

    public void hideAnimation(View triggerView, View hideView)
    {
        actionOtherVisible(false, triggerView, hideView);
    }

    public void setMyAnimListener(MyAnimListener myAnimListener)
    {
        this.myAnimListener = myAnimListener;
    }
}
