package com.fun_play.app.UI.Study.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;

import com.fun_play.app.R;
import com.fun_play.app.UI.Study.fragment.SystemCategoryFragment;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivitySystemDetailBinding;
import com.fun_play.app.datamanager.bean.study.SystemItemBean;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.view.MyFragmentPagerAdapter;
import com.fun_play.app.viewmodel.None.NoViewModel;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class SystemDetailActivity extends BaseActivity<NoViewModel,ActivitySystemDetailBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_detail);
        showContentView();
        isHideToolBar(true);
        initToolBar();
        initData();
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageView(this,0,bindingView.toolbar);
    }

    protected void initToolBar() {
        setSupportActionBar(bindingView.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        bindingView.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.finishAnimHorizontal(SystemDetailActivity.this);
            }
        });
    }

    private void initData() {
        int cid = getIntent().getIntExtra(Constant.CID, 0);
        SystemItemBean systemItemBean = (SystemItemBean) getIntent().getSerializableExtra(Constant.SystemItemBean);
        bindingView.setSystemItemBean(systemItemBean);

        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();

        int initIndex = 0;
        for (int i = 0, len = systemItemBean.getChildren().size(); i < len; i++) {
            SystemItemBean.ChildrenBean childrenBean = systemItemBean.getChildren().get(i);
            mTitleList.add(childrenBean.getName());
            if (childrenBean.getId() == cid) {
                initIndex = i;
                mFragments.add(SystemCategoryFragment.newInstance(childrenBean.getId(), childrenBean.getName(), true));
            } else {
                mFragments.add(SystemCategoryFragment.newInstance(childrenBean.getId(), childrenBean.getName(), false));
            }
        }

        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        bindingView.viewPager.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        bindingView.tabLayout.setupWithViewPager(bindingView.viewPager);

        // 设置初始位置
        bindingView.viewPager.setCurrentItem(initIndex);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UIManager.finishAnimHorizontal(SystemDetailActivity.this);
        return super.onKeyDown(keyCode, event);
    }

}
