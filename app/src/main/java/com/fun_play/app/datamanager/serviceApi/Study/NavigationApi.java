package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NavigationBean;
import com.fun_play.app.datamanager.service.Study.NavigationService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NavigationApi {

    private NavigationService navigationService;

    public NavigationApi() {
        this.navigationService = ServiceGenerator.createNoHeaderServiceFrom(NavigationService.class, UrlStore.API_WAN_ANDROID);
    }

    public void getNavigationData(RestAPIObserver<NavigationBean> restAPIObserver) {
        navigationService.getNavigationData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
