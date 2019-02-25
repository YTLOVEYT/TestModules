package com.example.modules.android.study.ui.fragment.home.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.modules.android.study.R;
import com.example.modules.android.study.adapter.SearchHistoryAdapter;
import com.example.modules.android.study.custom.other.FragmentDialogAnimation;
import com.example.modules.android.study.entity.search.HotSearchBean;
import com.example.modules.android.study.entity.search.SearchHistoryBean;
import com.example.modules.android.study.ui.fragment.BaseDialogFragment;
import com.example.modules.base.uitls.SoftInputUtil;
import com.example.modules.base.uitls.TipsUtil;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseDialogFragment implements View.OnClickListener,
        ViewTreeObserver.OnPreDrawListener, SearchContract.ISearchView
{

    @BindView(R.id.et_title_input)
    EditText etTitleInput;
    @BindView(R.id.tv_title_cancel)
    TextView tvTitleCancel;
    @BindView(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @BindView(R.id.tv_search_hot)
    TextView tvSearchHot;
    @BindView(R.id.tv_clear_search_history)
    TextView tvClearSearchHistory;
    @BindView(R.id.tv_search_emptyView)
    TextView tvSearchEmptyView;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;

    private List<HotSearchBean> hotSearchDataList;
    private FragmentDialogAnimation dialogAnimation;
    private SearchContract.ISearchPresenter searchPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //设置dialogFragment的样式
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.study_search_style);
        TipsUtil.logD("SearchFragment+onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        TipsUtil.logD("SearchFragment+onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        InitFragmentDialog();
    }

    @Override
    public void onDestroyView()
    {
        TipsUtil.logD("SearchFragment+onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        TipsUtil.logD("SearchFragment+onDestroy");
        super.onDestroy();
    }

    /** 设置Fragment全屏显示 */
    private void InitFragmentDialog()
    {
        Window window = getDialog().getWindow();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = (int) (displayMetrics.widthPixels * 0.98);
        if (window != null)
        {
            window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
            window.setGravity(Gravity.TOP);
            //window.setWindowAnimations(R.style);
        }
    }

    @Override
    protected int getFragmentLayoutId()
    {
        return R.layout.study_fragment_search;
    }

    @Override
    protected void InitViews(View view)
    {
        tvTitleCancel.setVisibility(View.VISIBLE);
        etTitleInput.setEnabled(true);
        etTitleInput.setFocusable(true);
        etTitleInput.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etTitleInput.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                TipsUtil.logD("event.getAction()=" + event.getAction());
                TipsUtil.logD("keyCode=" + keyCode);

                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER)
                    {
                        String input = etTitleInput.getText().toString().trim();
                        // FIXME: 2019/2/25 添加本地数据库
                        searchPresenter.saveSearchHistoryDataToDb(input);
                        //
                        backEvent();
                    }
                }
                return false;
            }
        });

        tvTitleCancel.setOnClickListener(this);
        //        etTitleInput.requestFocus();
        //        SoftInputUtil.openSoftInputKeyboard(context, etTitleInput);
        InitAnimation();
    }

    private void backEvent()
    {
        try
        {
            SoftInputUtil.closeSoftInputKeyboard(context, etTitleInput);
            dialogAnimation.hideAnimation(etTitleInput, rootView);
        }
        catch (Exception e)
        {
            TipsUtil.logE("back error");
        }
    }

    /** 初始化显示动画 */
    private void InitAnimation()
    {
        dialogAnimation = new FragmentDialogAnimation();
        dialogAnimation.setMyAnimListener(new FragmentDialogAnimation.MyAnimListener()
        {
            @Override
            public void onAnimationShowEnd()
            {
                TipsUtil.logD("onAnimationShowEnd");
                if (context != null)
                {
                    SoftInputUtil.openSoftInputKeyboard(context, etTitleInput);
                }
                else
                {
                    if (getActivity() != null)
                    {
                        SoftInputUtil.openSoftInputKeyboard(getActivity(), etTitleInput);
                    }
                }
            }

            @Override
            public void onAnimationHideEnd()
            {
                TipsUtil.logD("onAnimationHideEnd");
                etTitleInput.setText("");
                dismiss();
            }
        });
        etTitleInput.getViewTreeObserver().addOnPreDrawListener(this);
    }

    @Override
    protected void InitMVP()
    {
        if (searchPresenter == null)
        {
            searchPresenter = new SearchPresenter();
        }
        searchPresenter.attachView(this);
    }

    @Override
    protected void LoadData()
    {
        // FIXME: 2019/2/25 加载本地历史数据库
        searchPresenter.loadSearchHistoryDataFromDb();
        // FIXME: 2019/2/25 加载热搜数据
        searchPresenter.loadHotSearchData();
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.tv_title_cancel)
        {
            SoftInputUtil.closeSoftInputKeyboard(context, etTitleInput);
            dismiss();
        }
        else if (id == R.id.tv_clear_search_history)
        {

        }
    }

    @Override
    public boolean onPreDraw()
    {
        TipsUtil.logD("onPreDraw");
        etTitleInput.getViewTreeObserver().removeOnPreDrawListener(this);
        dialogAnimation.showAnimation(etTitleInput, rootView);
        return true;
    }

    @Override
    public Context getFragmentContext()
    {
        return getActivity() == null ? getActivity() : context;
    }

    @Override
    public void showSearchHistoryData(List<SearchHistoryBean> historyBeans)
    {
        if (historyBeans == null || historyBeans.size() <= 0)
        {
            return;
        }
        // FIXME: 2019/2/25 添加
        Collections.reverse(historyBeans);
        rvSearchHistory.setLayoutManager(new LinearLayoutManager(context));
        SearchHistoryAdapter historyAdapter = new SearchHistoryAdapter(historyBeans);
        rvSearchHistory.setAdapter(historyAdapter);
        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                SearchHistoryBean historyBean = (SearchHistoryBean) adapter.getItem(position);
                if (historyBean != null)
                {
                    String keyword = historyBean.getKeyword();
                    etTitleInput.setText(keyword);
                    etTitleInput.setSelection(etTitleInput.getText().length());
                }
            }
        });
    }

    @Override
    public void showHotSearchData(List<HotSearchBean> searchBeans)
    {

    }
}
