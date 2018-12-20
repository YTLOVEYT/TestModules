package com.example.modules.three.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.modules.base.arouter.ARouterUrls;
import com.example.modules.base.glouble.BaseActivity;
import com.example.modules.three.R;
import com.example.modules.three.R2;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterUrls.PATH_MODULE_THREE)
public class MainActivity extends BaseActivity
{
    @BindView(R2.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    private TextView tvOne;
    //autoWire不能为private
    @Autowired(name = "bundle")
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        tvOne.setText(bundle.getString("data"));
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.three_activity_main;
    }

    @Override
    protected void InitView()
    {
        tvOne = findViewById(R.id.tvText);
        tvTitle.setText(R.string.three_app_name);
    }

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
