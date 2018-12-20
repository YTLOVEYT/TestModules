package com.example.modules.one.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.modules.base.arouter.ARouterUrls;
import com.example.modules.base.arouter.ARouterUtils;
import com.example.modules.base.glouble.BaseActivity;
import com.example.modules.one.R;
import com.example.modules.one.R2;

import butterknife.BindView;

@Route(path = ARouterUrls.PATH_MODULE_ONE)
public class MainActivity extends BaseActivity implements View.OnClickListener
{

    @BindView(R2.id.bt_module_two)
    Button btModuleTwo;
    @BindView(R2.id.bt_module_three)
    Button btModuleThree;
    @BindView(R2.id.bt_module_four)
    Button btModuleFour;
    @BindView(R2.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R2.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SetListeners();

    }

    private void SetListeners()
    {
        btModuleTwo.setOnClickListener(this);
        btModuleThree.setOnClickListener(this);
        btModuleFour.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.one_activity_main;
    }

    @Override
    protected void InitView()
    {
        ivTitleLeft.setVisibility(View.INVISIBLE);
        tvTitle.setText(R.string.one_app_name);
    }

    /** 使用switch-case不起作用 */
    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.bt_module_two)
        {
            ARouterUtils.navigate(ARouterUrls.PATH_MODULE_TWO);
        }
        else if (id == R.id.bt_module_three)
        {
            Bundle bundle = new Bundle();
            bundle.putString("data", "moduleOne2moduleThree");
            ARouterUtils.navigate(ARouterUrls.PATH_MODULE_THREE, bundle);
        }
        else if (id == R.id.bt_module_four)
        {
            ARouterUtils.navigate(ARouterUrls.PATH_MODULE_FOUR);
        }
    }
}
