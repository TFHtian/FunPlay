package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Study.NewsDetailDataCallback;
import com.fun_play.app.ModelCallback.Study.WanAndroidBannerCallback;
import com.fun_play.app.ModelMVP.Study.contract.NewsDetailContract;
import com.fun_play.app.ModelMVP.Study.presenter.NewsDetailPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;

public class NewsDetailViewModel extends BaseViewModel implements NewsDetailContract.View{

    private NewsDetailContract.Presenter newsDetailPresenter;
    private NewsDetailDataCallback newsDetailDataCallback;

    public NewsDetailViewModel(@NonNull Application application) {
        super(application);
        newsDetailPresenter = new NewsDetailPresenterImpl(this);
    }

    public void SetCallback(NewsDetailDataCallback newsDetailDataCallback){
        this.newsDetailDataCallback = newsDetailDataCallback;
    }

    public void getNewsDetail(String aid){
        newsDetailPresenter.getNewsDetail(aid);
    }

    @Override
    public void NewsDetail(NewsArticleBean newsArticleBean) {
        newsDetailDataCallback.NewsDetail(newsArticleBean);
    }

}
