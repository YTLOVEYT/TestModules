package com.example.modules.android.study.ui.fragment.home.search;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.modules.android.study.R;
import com.example.modules.android.study.ui.fragment.BaseDialogFragment;
import com.example.modules.base.uitls.TipsUtil;

import butterknife.BindView;

public class SearchFragment extends BaseDialogFragment implements View.OnClickListener
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
            //            window.setWindowAnimations(R.style);
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

        tvTitleCancel.setOnClickListener(this);
        etTitleInput.requestFocus();


    }

    @Override
    protected void InitMVP()
    {

    }

    @Override
    protected void LoadData()
    {

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

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.tv_title_cancel)
        {
            dismiss();
        }
    }
}
