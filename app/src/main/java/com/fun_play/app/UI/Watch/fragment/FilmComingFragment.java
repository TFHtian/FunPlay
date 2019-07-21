package com.fun_play.app.UI.Watch.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fun_play.app.ModelCallback.Watch.FilmComingCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Watch.activity.FilmDetailActivity;
import com.fun_play.app.UI.Watch.adapter.FilmComingAdapter;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentFilmComingBinding;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;
import com.fun_play.app.datamanager.bean.watch.FilmItemBean;
import com.fun_play.app.utils.CommonUtils;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.watch.FilmComingViewModel;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


public class FilmComingFragment extends BaseFragment<FilmComingViewModel,FragmentFilmComingBinding> implements FilmComingCallback{

    private boolean mIsFirst = true;
    private boolean mIsPrepared = false;
    private boolean isLoadData = false;
    private ThreeBounce threeBounce;
    private FilmComingAdapter filmComingAdapter;

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
        return R.layout.fragment_film_coming;
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        if (mIsPrepared && mIsVisible &&mIsFirst){
            isLoadData = true;
            viewModel.refreshFilmComing();
        }
    }

    public void initView(){
        filmComingAdapter = new FilmComingAdapter();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        bindingView.recyclerComing.setLayoutManager(mLayoutManager);
        bindingView.recyclerComing.setAdapter(filmComingAdapter);
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(getActivity()));
    }

    public void initListener(){
        //刷新加载监听
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshFilmComing();
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.loadFilmComing();
            }
        });

        //item监听
        filmComingAdapter.setListener(new FilmComingAdapter.OnClickListener() {
            @Override
            public void onClick(ComingFilmBean.MoviecomingsBean bean, ImageView imageView) {
                FilmItemBean filmItemBean = new FilmItemBean();
                filmItemBean.setId(bean.getId());
                filmItemBean.setDN(bean.getDirector());
                filmItemBean.setTCn(bean.getTitle());
                filmItemBean.setTEn(bean.getReleaseDate());
                filmItemBean.setMovieType(bean.getType());
                filmItemBean.setImg(bean.getImage());
                filmItemBean.setLocationName(bean.getLocationName());
                String actor1 = bean.getActor1();
                String actor2 = bean.getActor2();
                if (!TextUtils.isEmpty(actor2)) {
                    actor1 = actor1 + " / " + actor2;
                }
                filmItemBean.setActors(actor1);
                Intent intent = new Intent(getActivity(), FilmDetailActivity.class);
                intent.putExtra(Constant.FilmBean, filmItemBean);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imageView, CommonUtils.getString(R.string.transition_movie_img));//与xml文件对应
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });
    }

    private void initAnimation() {
        threeBounce = new ThreeBounce();
        threeBounce.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(threeBounce);
        threeBounce.start();
    }


    @Override
    public void FilmComingData(ComingFilmBean comingFilmBean) {
        showAnimation(false);
        mIsFirst = false;
        if (isLoadData) {
            if (comingFilmBean.getMoviecomings()!=null){
                filmComingAdapter.setDataList(comingFilmBean.getMoviecomings());
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (comingFilmBean.getMoviecomings()!=null){
                filmComingAdapter.setNotifyDataChanged(comingFilmBean.getMoviecomings());
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
            threeBounce.start();
        }else {
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            bindingView.llLoading.setVisibility(View.GONE);
            threeBounce.stop();
        }
    }
}
