package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.SystemBean;
import com.fun_play.app.datamanager.service.Study.SystemService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SystemApi {

    private SystemService systemService;

    public SystemApi() {
        this.systemService = ServiceGenerator.createNoHeaderServiceFrom(SystemService.class, UrlStore.API_WAN_ANDROID);
    }

    public void getSystemData(RestAPIObserver<SystemBean> restAPIObserver) {
        systemService.getSystemData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
