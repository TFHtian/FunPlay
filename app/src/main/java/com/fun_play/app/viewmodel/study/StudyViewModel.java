package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelMVP.Study.contract.NewsListContract;
import com.fun_play.app.ModelMVP.Study.presenter.NewsListPresenterImpl;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.ModelCallback.Study.WanAndroidBannerCallback;
import com.fun_play.app.ModelMVP.Study.contract.StudyBannerContract;
import com.fun_play.app.ModelMVP.Study.contract.StudyGankIoContract;
import com.fun_play.app.ModelMVP.Study.presenter.StudyBannerPresenterImpl;
import com.fun_play.app.ModelMVP.Study.presenter.StudyGankIoPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;

import java.util.List;

public class StudyViewModel extends BaseViewModel implements StudyBannerContract.View,NewsListContract.View{

    private StudyBannerContract.Presenter studyBannerPresenter;
    private NewsListContract.Presenter newsListPresenter;
    private WanAndroidBannerCallback wanAndroidBannerCallback;
    private int page = 1;

    public StudyViewModel(@NonNull Application application) {
        super(application);
        studyBannerPresenter = new StudyBannerPresenterImpl(this);
        newsListPresenter = new NewsListPresenterImpl(this);
    }

    public void SetCallback(WanAndroidBannerCallback wanAndroidBannerCallback){
        this.wanAndroidBannerCallback = wanAndroidBannerCallback;
    }

    //获取玩安卓轮播图
    public void getWanAndroidBanner(){
        studyBannerPresenter.getWanAndroidBanner();
    }

    //玩安卓轮播图获取成功
    @Override
    public void WanAndroidBanner(WanAndroidBannerBean wanAndroidBannerBean) {
        wanAndroidBannerCallback.WanAndroidBanner(wanAndroidBannerBean);
        wanAndroidBannerCallback.ShowLoading(false);
    }

    //刷新新闻列表
    public void getRefreshNewsList(String id){
        page = 1;
        newsListPresenter.getNewsList(id,page);
    }

    //加载新闻列表
    public void getLoadNewsList(String id){
        page++;
        newsListPresenter.getNewsList(id,page);
    }

    //成获取新闻数据
    @Override
    public void NewsList(List<NewsDetail.ItemBean> itemBeanList) {
        wanAndroidBannerCallback.NewsList(itemBeanList);
    }
}
