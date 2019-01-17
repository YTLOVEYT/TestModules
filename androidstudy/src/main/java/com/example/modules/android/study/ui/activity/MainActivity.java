package com.example.modules.android.study.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.modules.android.study.R;
import com.example.modules.android.study.R2;
import com.example.modules.android.study.custom.BottomNavViewHelper;
import com.example.modules.android.study.ui.fragment.home.HomeFragment;
import com.example.modules.android.study.ui.fragment.knowledge.KnowledgeFragment;
import com.example.modules.android.study.ui.fragment.navigation.NavigationFragment;
import com.example.modules.android.study.ui.fragment.profile.ProfileFragment;
import com.example.modules.base.glouble.BaseActivity;
import com.example.modules.base.uitls.TipsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "")
public class MainActivity extends BaseActivity
{
    @BindView(R2.id.fl_layout_pager)
    FrameLayout flLayoutPager;
    @BindView(R2.id.bottom_nav_view)
    BottomNavigationView bottomNavView;
    @BindView(R2.id.nav)
    NavigationView nav;
    @BindView(R2.id.drawLayout)
    DrawerLayout drawLayout;

    private List<Fragment> fragments;
    private Fragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.study_activity_main;
    }

    @Override
    protected void InitView()
    {
        super.isRegistEventBus = true;

        InitNavView();
        toggleNavAndContent();
        InitFragment();
        InitBottomNavView();

    }

    /** FrameLayout内部视图加载 */
    private void InitFragment()
    {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new NavigationFragment());
        fragments.add(new ProfileFragment());

    }

    /** 初始化底部导航栏 */
    private void InitBottomNavView()
    {
        //使用反射修改bottomNavView，防止点击运动
        BottomNavViewHelper.disableShiftMode(bottomNavView);
        //添加默认视图
        addFragment(R.id.fl_layout_pager, fragments.get(0));
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int index = -1;
                switch (item.getItemId())
                {
                    case R.id.tab_home:
                        index = 0;
                        addFragment(R.id.fl_layout_pager, fragments.get(0));
                        break;
                    case R.id.tab_knowledge:
                        index = 1;
                        addFragment(R.id.fl_layout_pager, fragments.get(1));
                        break;
                    case R.id.tab_nav:
                        index = 2;
                        addFragment(R.id.fl_layout_pager, fragments.get(2));
                        break;
                    case R.id.tab_self:
                        index = 3;
                        addFragment(R.id.fl_layout_pager, fragments.get(3));
                        break;
                }
                TipsUtil.need_toast(MainActivity.this, "index=" + index);
                return true;
            }
        });
        bottomNavView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener()
        {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item)
            {
                int index = -1;
                switch (item.getItemId())
                {
                    case R.id.tab_home:
                        index = 0;
                        break;
                    case R.id.tab_knowledge:
                        index = 1;
                        break;
                    case R.id.tab_nav:
                        index = 2;
                        break;
                    case R.id.tab_self:
                        index = 3;
                        break;
                }
                TipsUtil.need_toast(MainActivity.this, "re_index=" + index);
            }
        });

    }

    /** 添加视图 */
    private void addFragment(int containerId, Fragment fragment)
    {
        if (fragment != null)
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded())    //已经存在
            {
                if (curFragment != null)
                {
                    transaction.hide(curFragment).show(fragment);
                }
                else
                {
                    transaction.show(fragment);
                }
            }
            else                     //不存在
            {
                if (curFragment != null)
                {
                    transaction.hide(curFragment).add(containerId, fragment);
                }
                else
                {
                    transaction.add(containerId, fragment);
                }
            }
            curFragment = fragment;
            transaction.commit();
        }
    }

    /** 初始化侧滑导航栏 */
    private void InitNavView()
    {
        // FIXME: 2018/12/20 控件监听
        nav.setItemIconTintList(null);//nav-icon显示颜色
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int index = -1;
                switch (item.getItemId())
                {
                    case R.id.nav_item_welfare:
                        index = 0;
                        break;
                    case R.id.nav_item_video:
                        index = 1;
                        break;
                    case R.id.nav_item_about_us:
                        index = 2;
                        break;
                    case R.id.nav_item_logout:
                        index = 3;
                        break;
                    case R.id.nav_item_setting:
                        index = 4;
                        break;
                }
                TipsUtil.need_toast(MainActivity.this, "index=" + index);
                if (drawLayout.isDrawerOpen(GravityCompat.START))
                {
                    drawLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    /** drawerLayout内部联动 */
    private void toggleNavAndContent()
    {
        //在drawLayout实现NavigationView与主页的动画
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawLayout, R.string.study_drawer_open, R.string.study_drawer_close)
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                View content = drawLayout.getChildAt(0);
                TipsUtil.logE("slideOffset=" + slideOffset); //0-1
                float scale = 1 - slideOffset;//1-0
                float endScale = 0.8f + scale * 0.2f;//1-0.8
                float startScale = 1 - 0.3f * scale;//

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                content.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                content.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                content.setScaleX(endScale);
                content.setScaleY(endScale);
            }
        };
        toggle.syncState();
        drawLayout.addDrawerListener(toggle);
    }

}
