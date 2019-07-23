package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.SystemListBean;
import com.fun_play.app.datamanager.service.Study.SystemCategoryService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SystemCategoryApi {

    private SystemCategoryService systemCategoryService;

    public SystemCategoryApi() {
        this.systemCategoryService = ServiceGenerator.createNoHeaderServiceFrom(SystemCategoryService.class, UrlStore.API_WAN_ANDROID);
    }

    public void getSystemList(RestAPIObserver<SystemListBean> restAPIObserver, int page, Integer cid) {
        systemCategoryService.getSystemList(page,cid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
