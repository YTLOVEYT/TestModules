package com.example.modules.two.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.modules.base.arouter.ARouterUrls;
import com.example.modules.base.glouble.BaseActivity;
import com.example.modules.two.R;
import com.example.modules.two.R2;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterUrls.PATH_MODULE_TWO)
public class MainActivity extends BaseActivity
{
    @BindView(R2.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    private TextView tvOne;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.two_activity_main;
    }

    @Override
    protected void InitView()
    {
        tvOne = findViewById(R.id.tv_one);
        tvTitle.setText(R.string.two_app_name);
    }

    /**
     * Resource IDs are non final in the library projects since SDK tools r14,
     * means that the library code cannot treat these IDs as constants
     */

    @OnClick({R2.id.iv_title_left, R2.id.tv_title})
    public void onViewClicked(View view)
    {
        int viewId = view.getId();
        if (viewId == R.id.iv_title_left)
        {
            MainActivity.this.finish();
        }
        else if (viewId == R.id.tv_title)
        {
            Toast.makeText(MainActivity.this, "title", Toast.LENGTH_SHORT).show();
        }
    }
}
