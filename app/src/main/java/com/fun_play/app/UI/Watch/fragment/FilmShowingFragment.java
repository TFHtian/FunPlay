package com.fun_play.app.UI.Watch.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fun_play.app.ModelCallback.Watch.FilmShowingCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Watch.adapter.FilmShowingAdapter;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentFilmShowingBinding;
import com.fun_play.app.datamanager.bean.watch.FilmItemBean;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.watch.FilmShowingViewModel;
import com.github.ybq.android.spinkit.style.Pulse;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class FilmShowingFragment extends BaseFragment<FilmShowingViewModel, FragmentFilmShowingBinding> implements FilmShowingCallback {

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadData = false;
    private Pulse pulse;
    private FilmShowingAdapter filmShowingAdapter;

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
        return R.layout.fragment_film_showing;
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            isLoadData = true;
            viewModel.refreshFilmShowing();
        }
    }

    public void initView(){
        filmShowingAdapter = new FilmShowingAdapter();
        bindingView.recyclerShowing.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerShowing.setAdapter(filmShowingAdapter);
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(getActivity()));
    }

    public void initListener(){
        //刷新加载监听
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshFilmShowing();
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.loadFilmShowing();
            }
        });
    }

    private void initAnimation() {
        pulse = new Pulse();
        pulse.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(pulse);
        pulse.start();
    }

    @Override
    public void FilmShowingData(List<FilmItemBean> filmItemBeanList) {
        showAnimation(false);
        mIsFirst = false;
        if (isLoadData) {
            if (filmItemBeanList!=null){
                filmShowingAdapter.setDataList(filmItemBeanList);
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (filmItemBeanList!=null){
                filmShowingAdapter.setNotifyDataChanged(filmItemBeanList);
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
            pulse.start();
        }else {
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            bindingView.llLoading.setVisibility(View.GONE);
            pulse.stop();
        }
    }
}
