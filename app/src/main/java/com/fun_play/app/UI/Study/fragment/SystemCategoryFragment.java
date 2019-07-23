package com.fun_play.app.UI.Study.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fun_play.app.ModelCallback.Study.SystemCategoryCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.SystemCategoryAdapter;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentSystemCategoryBinding;
import com.fun_play.app.datamanager.bean.study.ArticlesBean;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.study.SystemCategoryViewModel;
import com.github.ybq.android.spinkit.style.Circle;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class SystemCategoryFragment extends BaseFragment<SystemCategoryViewModel,FragmentSystemCategoryBinding> implements SystemCategoryCallback{

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadData = false;
    private int categoryId;
    private String categoryName;
    private Circle mCircleDrawable;
    private SystemCategoryAdapter systemCategoryAdapter;

    public static SystemCategoryFragment newInstance(int categoryId, String categoryName, boolean isLoad) {
        SystemCategoryFragment fragment = new SystemCategoryFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.CATEGORY_ID, categoryId);
        args.putString(Constant.CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getInt(Constant.CATEGORY_ID);
            categoryName = getArguments().getString(Constant.CATEGORY_NAME);
        }
    }

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
        return R.layout.fragment_system_category;
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

        systemCategoryAdapter = new SystemCategoryAdapter(getActivity());
        bindingView.recyclerSystem.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerSystem.setAdapter(systemCategoryAdapter);
    }

    public void initListener(){
        //刷新加载
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshSystem(categoryId);
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.loadSystem(categoryId);
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
            viewModel.refreshSystem(categoryId);
        }
    }

    @Override
    public void SystemList(List<ArticlesBean> articlesBeanList) {
        showAnimLoading(false);
        if (isLoadData) {
            if (articlesBeanList!=null){
                systemCategoryAdapter.setDataList(articlesBeanList);
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (articlesBeanList!=null){
                systemCategoryAdapter.setNotifyDataChanged(articlesBeanList);
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
