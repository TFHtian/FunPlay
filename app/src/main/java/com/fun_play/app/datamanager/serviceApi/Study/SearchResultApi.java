package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;
import com.fun_play.app.datamanager.service.Study.SearchResultService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchResultApi {

    private SearchResultService searchResultService;
    private SearchResultService searchResultServiceHot;

    public SearchResultApi() {
        this.searchResultService = ServiceGenerator.createNoHeaderServiceFrom(SearchResultService.class, UrlStore.API_GANKIO);
        this.searchResultServiceHot = ServiceGenerator.createNoHeaderServiceFrom(SearchResultService.class, UrlStore.API_WAN_ANDROID);
    }

    public void searchResult(RestAPIObserver<GankIoDataBean> restAPIObserver, int p, String type, String keyWord) {
        searchResultService.searchResult(p,type,keyWord)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

    public void getHotKey(RestAPIObserver<SearchTagBean> restAPIObserver) {
        searchResultServiceHot.getHotKey()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
