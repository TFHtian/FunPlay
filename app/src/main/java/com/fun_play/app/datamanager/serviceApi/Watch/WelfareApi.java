package com.fun_play.app.datamanager.serviceApi.Watch;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.service.Watch.WelfareService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WelfareApi {

    private WelfareService welfareService;

    public WelfareApi() {
        this.welfareService = ServiceGenerator.createNoHeaderServiceFrom(WelfareService.class, UrlStore.API_GANKIO);
    }

    public void getWelfareData(RestAPIObserver<GankIoDataBean> restAPIObserver,String id,int page,int pre_page) {
        welfareService.getWelfareData(id,page,pre_page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
