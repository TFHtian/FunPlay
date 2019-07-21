package com.fun_play.app.UI.Study.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;
import com.fun_play.app.ModelCallback.Study.StudyGankCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.GankAndroidAdapter;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityGankBinding;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.utils.SPUtils;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.study.GankViewModel;
import com.github.ybq.android.spinkit.style.Wave;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class GankActivity extends BaseActivity<GankViewModel, ActivityGankBinding> implements StudyGankCallback {

    private Wave mWaveDrawable;
    private boolean isLoadData = false;
    private String type = Constant.All_Type;
    private BottomSheet.Builder builder = null;
    private GankAndroidAdapter gankAndroidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        showContentView();
        viewModel.setCallback(this);
        initView();
        initBottomPopup();
        initAnimation();
        loadData();
        initListener();
    }

    public void initView(){
        isHideToolBar(false);
        setCenterTitle(getResources().getString(R.string.gank_title));

        gankAndroidAdapter = new GankAndroidAdapter(this);
        bindingView.recyclerGanHuo.setLayoutManager(new LinearLayoutManager(this));
        bindingView.recyclerGanHuo.setAdapter(gankAndroidAdapter);
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(this));
    }

    //初始加载动画
    public void initAnimation(){
        mWaveDrawable = new Wave();
        mWaveDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivListLoading.setImageDrawable(mWaveDrawable);
        mWaveDrawable.start();
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

    public void loadData(){
        isLoadData = true;
        viewModel.refreshGank(type);
    }

    @Override
    public void GankIoData(GankIoDataBean gankIoDataBean) {
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
            bindingView.llListLoading.setVisibility(View.VISIBLE);
            bindingView.refreshLayout.setVisibility(View.GONE);
            mWaveDrawable.start();
        } else {
            bindingView.llListLoading.setVisibility(View.GONE);
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            mWaveDrawable.stop();
        }
    }

    private void initBottomPopup() {
        try {
            builder = new BottomSheet.Builder(this, R.style.BottomSheet_StyleDialog)
                    .title(R.string.select_category)
                    .sheet(R.menu.gank_bottomsheet)
                    .listener((dialog, which) -> {
                        switch (which) {
                            case R.id.gank_all:
                                if (isOtherType(Constant.All)) {
                                    changeContent(Constant.All,Constant.All_Type);
                                }
                                break;
                            case R.id.gank_ios:
                                if (isOtherType(Constant.IOS)) {
                                    changeContent(Constant.IOS,Constant.IOS_Type);
                                }
                                break;
                            case R.id.gank_qian:
                                if (isOtherType(Constant.Web)) {
                                    changeContent(Constant.Web,Constant.Web);
                                }
                                break;
                            case R.id.gank_app:
                                if (isOtherType(Constant.App)) {
                                    changeContent(Constant.App,Constant.App);
                                }
                                break;
                            case R.id.gank_movie:
                                if (isOtherType(Constant.Movie)) {
                                    changeContent(Constant.Movie,Constant.Movie);
                                }
                                break;
                            case R.id.gank_resouce:
                                if (isOtherType(Constant.Source)) {
                                    changeContent(Constant.Source,Constant.Source);
                                }
                                break;
                            default:
                                break;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        bindingView.llChooseCatalogue.setOnClickListener(v -> {
            if (builder != null) {
                builder.show();
            }
        });
    }

    private void changeContent(String name,String content) {
        bindingView.txName.setText(name);
        type = content;
        isLoadData = true;
        viewModel.refreshGank(type);
        showAnimLoading(true);
        SPUtils.putString(Constant.GANK_CALA, content);
    }

    private boolean isOtherType(String selectType) {
        String clickText = SPUtils.getString(Constant.GANK_CALA, Constant.All);
        if (clickText.equals(selectType)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBackClick(View view) {
        super.onBackClick(view);
        UIManager.finishAnimHorizontal(GankActivity.this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UIManager.finishAnimHorizontal(GankActivity.this);
        return super.onKeyDown(keyCode, event);
    }
}
