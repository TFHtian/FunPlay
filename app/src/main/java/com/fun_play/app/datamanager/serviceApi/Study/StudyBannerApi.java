package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.datamanager.service.Study.StudyBannerService;
import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StudyBannerApi {

    private StudyBannerService studyBannerService;

    public StudyBannerApi() {
        this.studyBannerService = ServiceGenerator.createNoHeaderServiceFrom(StudyBannerService.class, UrlStore.API_WAN_ANDROID);
    }

    public void getWanAndroidBanner(RestAPIObserver<WanAndroidBannerBean> restAPIObserver) {
        studyBannerService.getWanAndroidBanner()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
