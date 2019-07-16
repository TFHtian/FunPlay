package com.fun_play.app.UI.Watch.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fun_play.app.ModelCallback.Watch.WelfareCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Watch.adapter.WelfareAdapter;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentWelfareBinding;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.watch.WelfareViewModel;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WelfareFragment extends BaseFragment<WelfareViewModel, FragmentWelfareBinding> implements WelfareCallback {

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadData = false;
    private DoubleBounce doubleBounce;
    private WelfareAdapter welfareAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        viewModel.setCallback(this);
        initAnimation();
        initView();
        initListener();
        mIsPrepared = true;
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_welfare;

    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            isLoadData = true;
            viewModel.refreshWelfare();
        }
    }

    public void initView(){
        welfareAdapter = new WelfareAdapter();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        bindingView.recyclerWelfare.setLayoutManager(mLayoutManager);
        bindingView.recyclerWelfare.setAdapter(welfareAdapter);
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(getActivity()));
    }

    public void initListener(){
        //刷新加载监听
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshWelfare();
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.laodWelfare();
            }
        });
    }

    private void initAnimation() {
        doubleBounce = new DoubleBounce();
        doubleBounce.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(doubleBounce);
        doubleBounce.start();
    }

    @Override
    public void WelfareData(GankIoDataBean gankIoDataBean) {
        showAnimation(false);
        mIsFirst = false;
        if (isLoadData) {
            if (gankIoDataBean.getResults()!=null){
                welfareAdapter.setDataList(gankIoDataBean.getResults());
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (gankIoDataBean.getResults()!=null){
                welfareAdapter.setNotifyDataChanged(gankIoDataBean.getResults());
                bindingView.refreshLayout.finishLoadMore();
            }else {
                bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    public void showAnimation(boolean isShow){
        if (isShow){
            bindingView.refreshLayout.setVisibility(View.GONE);
            bindingView.llLoading.setVisibility(View.VISIBLE);
            doubleBounce.start();
        }else {
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            bindingView.llLoading.setVisibility(View.GONE);
            doubleBounce.stop();
        }
    }

}
