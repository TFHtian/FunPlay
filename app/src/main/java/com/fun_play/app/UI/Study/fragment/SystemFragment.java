package com.fun_play.app.UI.Study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fun_play.app.ModelCallback.Study.SystemCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.SystemAdapter;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentSystemBinding;
import com.fun_play.app.datamanager.bean.study.SystemItemBean;
import com.fun_play.app.viewmodel.study.SystemViewModel;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.List;

public class SystemFragment extends BaseFragment<SystemViewModel,FragmentSystemBinding> implements SystemCallback{

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private Wave mWaveDrawable;
    private SystemAdapter systemAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initAnimation();
        initView();
        // 准备就绪
        mIsPrepared = true;
        viewModel.setCallback(this);
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_system;
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            viewModel.getSystemData();
        }
    }

    public void initView(){
        systemAdapter = new SystemAdapter();
        bindingView.recycleSystem.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recycleSystem.setAdapter(systemAdapter);
    }

    //初始加载动画
    public void initAnimation(){
        mWaveDrawable = new Wave();
        mWaveDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(mWaveDrawable);
        mWaveDrawable.start();
    }

    @Override
    public void SystemData(List<SystemItemBean> systemItemBeanList) {
        showAnimLoading(false);
        systemAdapter.setDataList(systemItemBeanList);
    }

    //是否显示动画
    private void showAnimLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.recycleSystem.setVisibility(View.GONE);
            mWaveDrawable.start();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.recycleSystem.setVisibility(View.VISIBLE);
            mWaveDrawable.stop();
        }
    }
}
