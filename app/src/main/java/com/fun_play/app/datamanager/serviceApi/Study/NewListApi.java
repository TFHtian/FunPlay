package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.datamanager.service.Study.NewsListService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewListApi {

    private NewsListService newsListService;

    public NewListApi() {
        this.newsListService = ServiceGenerator.createNoHeaderServiceFrom(NewsListService.class, UrlStore.News_Article_Cmpp_Api);
    }

    public void getNewsList(RestAPIObserver<List<NewsDetail>> restAPIObserver, String id, int page) {
        newsListService.getNewsList(id,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }
}
