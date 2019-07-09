package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.WanAndroidBean;
import com.fun_play.app.datamanager.service.Study.WanAndroidListService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WanAndroidListApi {

    private WanAndroidListService wanAndroidListService;

    public WanAndroidListApi() {
        this.wanAndroidListService = ServiceGenerator.createNoHeaderServiceFrom(WanAndroidListService.class, UrlStore.API_WAN_ANDROID);
    }

    public void getWanAndroidData(RestAPIObserver<WanAndroidBean> restAPIObserver, int cid) {
        wanAndroidListService.getWanAndroidData(cid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
