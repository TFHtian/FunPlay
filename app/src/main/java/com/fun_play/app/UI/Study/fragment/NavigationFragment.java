package com.fun_play.app.UI.Study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fun_play.app.ModelCallback.Study.NavigationCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.NavigationContentAdapter;
import com.fun_play.app.UI.Study.adapter.NavigationTitleAdapter;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentNavigationBinding;
import com.fun_play.app.datamanager.bean.study.NavigationBean;
import com.fun_play.app.viewmodel.study.NavigationViewModel;
import com.github.ybq.android.spinkit.style.Wave;

public class NavigationFragment extends BaseFragment<NavigationViewModel,FragmentNavigationBinding> implements NavigationCallback{

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private Wave mWaveDrawable;
    private NavigationTitleAdapter navigationTitleAdapter;
    private NavigationContentAdapter navigationContentAdapter;
    private int currentPosition = 0;
    private LinearLayoutManager layoutManagerTitle;

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
        return R.layout.fragment_navigation;
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            viewModel.getNavigationData();
        }
    }

    //初始加载动画
    public void initAnimation(){
        mWaveDrawable = new Wave();
        mWaveDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(mWaveDrawable);
        mWaveDrawable.start();
    }

    public void initView(){

        navigationTitleAdapter = new NavigationTitleAdapter();
        layoutManagerTitle = new LinearLayoutManager(getActivity());
        bindingView.recycleNavigationTitle.setLayoutManager(layoutManagerTitle);
        bindingView.recycleNavigationTitle.setAdapter(navigationTitleAdapter);

        LinearLayoutManager layoutManagerContent = new LinearLayoutManager(getActivity());
        bindingView.recycleNavigationContent.setLayoutManager(layoutManagerContent);
        navigationContentAdapter = new NavigationContentAdapter();
        bindingView.recycleNavigationContent.setAdapter(navigationContentAdapter);

        navigationTitleAdapter.setOnSelectListener(new NavigationTitleAdapter.OnSelectListener() {
            @Override
            public void onSelected(int position) {
                selectItem(position);
                moveToCenter(position);
                layoutManagerContent.scrollToPositionWithOffset(position, 0);
            }
        });
        bindingView.recycleNavigationContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int itemPosition = layoutManagerContent.findFirstVisibleItemPosition();
                if (currentPosition != itemPosition) {
                    selectItem(itemPosition);
                    moveToCenter(itemPosition);
                }
            }
        });
    }

    @Override
    public void NavigationData(NavigationBean navigationBean) {
        showAnimLoading(false);
        navigationTitleAdapter.clear();
        navigationTitleAdapter.addAll(navigationBean.getData());
        navigationTitleAdapter.notifyDataSetChanged();
        selectItem(0);

        navigationContentAdapter.clear();
        navigationContentAdapter.addAll(navigationBean.getData());
        navigationContentAdapter.notifyDataSetChanged();

        mIsFirst = false;
    }

    private void selectItem(int position) {
        if (position < 0 || navigationTitleAdapter.getData().size() < position) {
            return;
        }
        navigationTitleAdapter.getData().get(currentPosition).setSelected(false);
        navigationTitleAdapter.notifyItemChanged(currentPosition);
        currentPosition = position;
        navigationTitleAdapter.getData().get(position).setSelected(true);
        navigationTitleAdapter.notifyItemChanged(position);
    }

    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = bindingView.recycleNavigationTitle.getChildAt(position - layoutManagerTitle.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - bindingView.recycleNavigationTitle.getHeight() / 2);
            bindingView.recycleNavigationTitle.smoothScrollBy(0, y);
        }
    }

    //是否显示动画
    private void showAnimLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.llContent.setVisibility(View.GONE);
            mWaveDrawable.start();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.llContent.setVisibility(View.VISIBLE);
            mWaveDrawable.stop();
        }
    }

}
