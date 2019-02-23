package com.example.modules.android.study.ui.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.modules.android.study.R;
import com.example.modules.android.study.R2;
import com.example.modules.android.study.adapter.HomeListAdapter;
import com.example.modules.android.study.entity.article.ArticleData;
import com.example.modules.android.study.entity.banner.BannerData;
import com.example.modules.android.study.ui.fragment.BaseFragment;
import com.example.modules.android.study.ui.fragment.home.search.SearchFragment;
import com.example.modules.base.event.BaseEvent;
import com.example.modules.base.uitls.TipsUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 * Create By YinTao On 2018/12/23 15:57
 */
public class HomeFragment extends BaseFragment implements OnRefreshListener,
        OnLoadMoreListener,
        View.OnClickListener,
        HomeContract.IHomeView
{
    @BindView(R2.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R2.id.ll_title_search)
    LinearLayout llTitleSearch;
    @BindView(R2.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R2.id.recycleView_home)
    RecyclerView recycleViewHome;
    @BindView(R2.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayoutHome;
    @BindView(R.id.et_title_input)
    EditText etTitleInput;

    private int page = 0;
    private int pageSize = 20;

    private HomeContract.IHomePresenter homePresenter;

    private List<ArticleData> articleDataList;//数据2
    private Banner banner;
    private HomeListAdapter homeListAdapter;
    private boolean isRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.isRegEventBus = true;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        TipsUtil.logE("homeFragment=" + hidden);
        if (hidden)
        {
            banner.stopAutoPlay();
        }
        else
        {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    protected int getFragmentLayoutId()
    {
        return R.layout.study_fragment_home;
    }

    @Override
    protected void InitViews(View view)
    {
        ivTitleLeft.setVisibility(View.VISIBLE);
        ivTitleRight.setVisibility(View.VISIBLE);
        etTitleInput.setInputType(EditorInfo.TYPE_NULL);
        etTitleInput.setHint("点我搜索");
        InitRecycleView(); //初始化RecycleView
        ivTitleLeft.setOnClickListener(this);
        llTitleSearch.setOnClickListener(this);
        etTitleInput.setOnClickListener(this);
        ivTitleRight.setOnClickListener(this);
        smartRefreshLayoutHome.setOnRefreshListener(this);
        smartRefreshLayoutHome.setOnLoadMoreListener(this);
    }

    /** 初始化RecycleView */
    private void InitRecycleView()
    {
        articleDataList = new ArrayList<>();
        homeListAdapter = new HomeListAdapter(articleDataList);
        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                // FIXME: 2018/12/25 跳转界面
                TipsUtil.need_toast(getActivity(), "position=" + position + "-" + view.getId());
            }
        });

        homeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                // FIXME: 2018/12/25 内部点击事件
                TipsUtil.need_toast(getActivity(), "c_position=" + position + "--" + view.getId());
            }

        });
        recycleViewHome.setLayoutManager(new LinearLayoutManager(getFragmentContext()));
        LinearLayout header = (LinearLayout) LayoutInflater.from(getFragmentContext())
                .inflate(R.layout.study_recycle_banner, null);
        banner = header.findViewById(R.id.banner_home_item_header);
        //        header.removeView(banner);
        homeListAdapter.addHeaderView(header);
        recycleViewHome.setAdapter(homeListAdapter);
    }

    @Override
    protected void InitMVP()
    {
        if (homePresenter == null)
        {
            homePresenter = new HomePresenter();
            getLifecycle().addObserver(homePresenter);
        }
        homePresenter.attachView(this);
    }

    @Override
    protected void LoadData()
    {
        homePresenter.loadBannerData();//加载Banner数据
        smartRefreshLayoutHome.autoRefresh();//加载列表数据
    }


    @Override
    public Context getFragmentContext()
    {
        return getActivity();
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout)
    {
        isRefresh = true;
        this.page = 0;
        loadListData(page, pageSize);
    }

    /**
     * 加载列表数据
     * @param page     页数
     * @param pageSize 页大小
     */
    private void loadListData(int page, int pageSize)
    {
        homePresenter.loadArticleList(page);
    }


    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.iv_title_left)
        {
            // FIXME: 2019/1/4 发信息到主窗口弹出界面
            EventBus.getDefault().post(new BaseEvent<String>(0, "Open_Drawer"));
        }
        else if (id == R.id.ll_title_search || id == R.id.et_title_input)
        {
            TipsUtil.need_toast(getActivity(), "search");
            SearchFragment searchFragment = new SearchFragment();
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null)
            {
                searchFragment.show(fragmentManager, "searchFragment");
            }
            else
            {
                TipsUtil.logE("search_fragment error");
            }
        }
        else if (id == R.id.iv_title_right)
        {
            TipsUtil.need_toast(getActivity(), "right");
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout)
    {
        isRefresh = false;
        page++;
        loadListData(page, pageSize);
    }

    @Override
    public void updateBanner(List<BannerData> bannerData)
    {
        if (bannerData == null || bannerData.size() == 0 || banner == null)
        {
            TipsUtil.logE("数据/banner为空");
            return;
        }
        ArrayList<String> bannerTitleList = new ArrayList<>();
        ArrayList<String> bannerImageList = new ArrayList<>();
        ArrayList<String> bannerUrlList = new ArrayList<>();
        for (BannerData data : bannerData)
        {
            bannerTitleList.add(data.getTitle());
            bannerImageList.add(data.getImagePath());
            bannerUrlList.add(data.getUrl());
        }
        banner.setImages(bannerImageList);
        banner.setBannerTitles(bannerTitleList);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.setImageLoader(new ImageLoader()
        {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView)
            {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setOnBannerListener(new OnBannerListener()
        {
            @Override
            public void OnBannerClick(int position)
            {
                TipsUtil.need_toast(getFragmentContext(), "点击 " + position);
            }
        });
        banner.start();

    }

    @Override
    public void updateListData(List<ArticleData> articleData)
    {
        if (articleData == null || articleData.size() == 0)
        {
            return;
        }
        if (isRefresh)   //刷新
        {
            articleDataList = articleData;
            homeListAdapter.replaceData(articleDataList);
        }
        else
        {
            articleDataList.addAll(articleData);
            homeListAdapter.addData(articleData);
        }
        smartRefreshLayoutHome.finishLoadMore();
        smartRefreshLayoutHome.finishRefresh();

    }
}
