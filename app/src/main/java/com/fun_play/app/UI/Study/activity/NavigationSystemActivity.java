package com.fun_play.app.UI.Study.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;

import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.TabViewPageAdapter;
import com.fun_play.app.UI.Study.fragment.NavigationFragment;
import com.fun_play.app.UI.Study.fragment.SystemFragment;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityAndroidFamilyBinding;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.viewmodel.None.NoViewModel;

public class NavigationSystemActivity extends BaseActivity<NoViewModel,ActivityAndroidFamilyBinding>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_family);
        showContentView();
        isHideToolBar(true);
        setToolBar();
        initView();
    }

    public void setToolBar() {
        setSupportActionBar(bindingView.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        bindingView.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.finishAnimHorizontal(NavigationSystemActivity.this);
            }
        });
    }

    public void initView(){
        bindingView.toolBar.setTitle(R.string.study_category_two);
        setViewPage();
        bindingView.tabAndroid.setupWithViewPager(bindingView.viewpager);
        bindingView.tabAndroid.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));
    }

    public void setViewPage(){
        TabViewPageAdapter adapter = new TabViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SystemFragment(), getResources().getString(R.string.study_system));
        adapter.addFragment(new NavigationFragment(),getResources().getString(R.string.study_navigation));
        bindingView.viewpager.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UIManager.finishAnimHorizontal(NavigationSystemActivity.this);
        return super.onKeyDown(keyCode, event);
    }
}

