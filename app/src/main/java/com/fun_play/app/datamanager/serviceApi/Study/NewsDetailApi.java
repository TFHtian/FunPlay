package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.datamanager.service.Study.NewsDetailService;
import com.fun_play.app.datamanager.service.Study.NewsListService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailApi {

    private NewsDetailService newsDetailService;

    public NewsDetailApi() {
        this.newsDetailService = ServiceGenerator.createNoHeaderServiceFrom(NewsDetailService.class, UrlStore.News_Article_Cmpp_Api);
    }

    public void getNewsDetail(RestAPIObserver<NewsArticleBean> restAPIObserver, String aid) {
        newsDetailService.getNewsDetail(aid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
