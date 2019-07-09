package com.fun_play.app.UI.Study.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fun_play.app.ModelCallback.Study.StudyGankCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.GankAndroidAdapter;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentLargeAndroidBinding;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.study.GankViewModel;
import com.github.ybq.android.spinkit.style.Circle;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class LargeAndroidFragment extends BaseFragment<GankViewModel, FragmentLargeAndroidBinding> implements StudyGankCallback {

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadData = false;
    private String type = Constant.Android;
    private Circle mCircleDrawable;
    private GankAndroidAdapter gankAndroidAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initAnimation();
        initView();
        initListener();
        viewModel.setCallback(this);
        mIsPrepared = true;
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_large_android;
    }

    private void initAnimation() {
        //整体动画，获取到banner就停止
        mCircleDrawable = new Circle();
        mCircleDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(mCircleDrawable);
        mCircleDrawable.start();
    }

    public void initView(){
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(getActivity()));
        gankAndroidAdapter = new GankAndroidAdapter();
        bindingView.recyclerLarge.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerLarge.setAdapter(gankAndroidAdapter);
    }

    public void initListener(){
        //刷新加载监听
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshGank(type);
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.loadGank(type);
            }
        });
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            isLoadData = true;
            viewModel.refreshGank(type);
        }
    }

    @Override
    public void GankIoData(GankIoDataBean gankIoDataBean) {
        mIsFirst = false;
        showAnimLoading(false);
        if (isLoadData) {
            if (gankIoDataBean.getResults()!=null){
                gankAndroidAdapter.setDataList(gankIoDataBean.getResults());
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (gankIoDataBean.getResults()!=null){
                gankAndroidAdapter.setNotifyDataChanged(gankIoDataBean.getResults());
                bindingView.refreshLayout.finishLoadMore();
            }else {
                bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    //是否显示动画
    private void showAnimLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.refreshLayout.setVisibility(View.GONE);
            mCircleDrawable.start();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            mCircleDrawable.stop();
        }
    }

}
