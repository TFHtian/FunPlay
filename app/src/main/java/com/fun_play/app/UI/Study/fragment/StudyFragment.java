package com.fun_play.app.UI.Study.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.fun_play.app.UI.Study.activity.NewsDetailActivity;
import com.fun_play.app.UI.Study.adapter.NewsListAdapter;
import com.fun_play.app.datamanager.bean.study.FilterBean;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.NewFilterBean;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.ModelCallback.Study.WanAndroidBannerCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.GankAndroidAdapter;
import com.fun_play.app.UI.Study.adapter.StudyTabAdapter;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentStudyBinding;
import com.fun_play.app.utils.DensityUtil;
import com.fun_play.app.utils.GlideUtil;
import com.fun_play.app.utils.PerfectClickListener;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.study.StudyViewModel;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Wave;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import me.jingbin.sbanner.config.ScaleRightTransformer;
import me.jingbin.sbanner.holder.BannerViewHolder;

public class StudyFragment extends BaseFragment<StudyViewModel, FragmentStudyBinding> implements WanAndroidBannerCallback,NewsListAdapter.NewsListListener {

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadBanner = false;
    private boolean isLoadData = false;
    private Wave mWaveDrawable;
    private ChasingDots mChasingDotsDrawable;
    private List<NewFilterBean> mTitles = new ArrayList<>();
    private Integer currentSelected = 0;//默认为第一个tab选项
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private StudyTabAdapter studyTabAdapter;
    private NewsListAdapter newsListAdapter;
    private String newsTypeId = Constant.NewsCommonId;
    // banner图的宽
    private int width;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initAnimation();
        initView();
        initListener();
        viewModel.SetCallback(this);
        // 准备就绪
        mIsPrepared = true;
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_study;
    }

    @Override
    protected void loadData() {
        if (mIsPrepared && isLoadBanner) {
            onResume();
        }
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            viewModel.getWanAndroidBanner();
        }
    }

    public void initView(){
        //banner图
        width = DensityUtil.getDisplayWidth() - DensityUtil.dip2px(160);
        float height = width / 1.8f;
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) height);
        bindingView.banner.setLayoutParams(lp);

        //初始化tab
        mTitles = Constant.getNewsTab();
        for (NewFilterBean filterBean : mTitles) {
            mFragments.add(SimpleTabFragment.getInstance(filterBean.getOption()));
        }
        studyTabAdapter = new StudyTabAdapter(getChildFragmentManager(),mFragments,mTitles);
        bindingView.viewPageOrder.setAdapter(studyTabAdapter);
        bindingView.orderTab.setViewPager(bindingView.viewPageOrder);
        bindingView.orderTab.setCurrentTab(currentSelected);//默认选择第一个

        //新闻列表
        newsListAdapter = new NewsListAdapter(this);
        bindingView.recyclerGank.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recyclerGank.setAdapter(newsListAdapter);
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(getActivity()));
    }

    public void initListener(){
        //tab监听
        bindingView.orderTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                isLoadData = true;
                newsTypeId = mTitles.get(position).getId();
                viewModel.getRefreshNewsList(newsTypeId);
                showListAnimLoading(true);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //刷新加载监听
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.getRefreshNewsList(newsTypeId);
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.getLoadNewsList(newsTypeId);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isLoadBanner) {
            bindingView.banner.startAutoPlay();
        }
    }

    @Override
    protected void onInvisible() {
        if (mIsPrepared && isLoadBanner) {
            onPause();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // 不可见时轮播图停止滚动
        if (isLoadBanner) {
            bindingView.banner.stopAutoPlay();
        }
    }

    //玩安卓轮播图
    @Override
    public void WanAndroidBanner(WanAndroidBannerBean wanAndroidBannerBean) {
        if (mIsFirst){
            mIsFirst = false;
        }
        showBannerView(wanAndroidBannerBean.getData());
        //成功获取banner之后再去获取新闻列表
        isLoadData = true;
        viewModel.getRefreshNewsList(newsTypeId);
    }

    //是否展示加载动画
    @Override
    public void ShowLoading(Boolean isShowLoading) {
        //获取到banner显示整体，显示列表加载动画
        showRotaLoading(isShowLoading);
        showListAnimLoading(true);
    }

    //新闻列表数据
    @Override
    public void NewsList(List<NewsDetail.ItemBean> itemBeanList) {
        showListAnimLoading(false);
        if (isLoadData) {
            if (itemBeanList!=null){
                newsListAdapter.setDataList(itemBeanList);
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (itemBeanList!=null){
                newsListAdapter.setNotifyDataChanged(itemBeanList);
                bindingView.refreshLayout.finishLoadMore();
            }else {
                bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    //初始化动画视图
    private void initAnimation() {
        //整体动画，获取到banner就停止
        bindingView.llLoading.setVisibility(View.VISIBLE);
        mChasingDotsDrawable = new ChasingDots();
        mChasingDotsDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(mChasingDotsDrawable);
        mChasingDotsDrawable.start();
        //列表动画，获取到列表停止
        mWaveDrawable = new Wave();
        mWaveDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivListLoading.setImageDrawable(mWaveDrawable);
    }

    //是否显示整体动画
    private void showRotaLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.refreshLayout.setVisibility(View.GONE);
            mChasingDotsDrawable.start();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            mChasingDotsDrawable.stop();
        }
    }

    //是否显示列表加载数据动画
    private void showListAnimLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llListLoading.setVisibility(View.VISIBLE);
            bindingView.recyclerGank.setVisibility(View.GONE);
            mWaveDrawable.start();
        } else {
            bindingView.llListLoading.setVisibility(View.GONE);
            bindingView.recyclerGank.setVisibility(View.VISIBLE);
            mWaveDrawable.stop();
        }
    }

    //设置banner图
    public void showBannerView(List<WanAndroidBannerBean.DataBean> result) {
        bindingView.banner
                .setIndicatorRes(R.drawable.banner_red, R.drawable.banner_grey)
                .setBannerAnimation(ScaleRightTransformer.class)
                .setDelayTime(5000)
                .setPages(result, CustomViewHolder::new)
                .start();
        bindingView.banner.stopAutoPlay();
        isLoadBanner = true;
    }

    //新闻item点击监听
    @Override
    public void clickNews(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.AID,id);
        UIManager.switcherAnimHorizontal(getActivity(), NewsDetailActivity.class,bundle);
    }

    class CustomViewHolder implements BannerViewHolder<WanAndroidBannerBean.DataBean> {

        private RoundedImageView imageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner_wanandroid, null);
            imageView = view.findViewById(R.id.iv_banner);
            return view;
        }

        @Override
        public void onBind(Context context, int position, WanAndroidBannerBean.DataBean data) {
            if (data != null) {
                DensityUtil.formatHeight(imageView, width, 1.8f, 3);
                GlideUtil.displayEspImage(data.getImagePath(), imageView, 3);
                imageView.setOnClickListener(new PerfectClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {

                    }
                });
            }
        }
    }

}
