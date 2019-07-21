package com.fun_play.app.UI.Study.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fun_play.app.ModelCallback.Study.WanAndroidCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.GankAndroidAdapter;
import com.fun_play.app.UI.Study.adapter.WanAndroidListAdapter;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentPlayAndroidBinding;
import com.fun_play.app.datamanager.bean.study.WanAndroidData;
import com.fun_play.app.utils.ToastUtil;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.study.PlayAndroidViewModel;
import com.github.ybq.android.spinkit.style.Circle;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class PlayAndroidFragment extends BaseFragment<PlayAndroidViewModel, FragmentPlayAndroidBinding> implements WanAndroidCallback {

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadData = false;
    private Circle mCircleDrawable;
    private WanAndroidListAdapter wanAndroidListAdapter;

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
        return R.layout.fragment_play_android;
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

        wanAndroidListAdapter = new WanAndroidListAdapter(getActivity());
        bindingView.recyclerWan.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerWan.setAdapter(wanAndroidListAdapter);
    }

    public void initListener(){
        //刷新加载监听
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshData();
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.loadData();
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
            viewModel.refreshData();
        }
    }


    @Override
    public void WanAndroidData(List<WanAndroidData> wanAndroidDataList) {
        showAnimLoading(false);
        mIsFirst = false;
        if (isLoadData) {
            if (wanAndroidDataList!=null){
                wanAndroidListAdapter.setDataList(wanAndroidDataList);
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (wanAndroidDataList!=null){
                wanAndroidListAdapter.setNotifyDataChanged(wanAndroidDataList);
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

