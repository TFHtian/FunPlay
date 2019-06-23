package com.fun_play.app.datamanager.serviceApi.Study;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.service.Study.StudyGankIoService;
import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StudyGankIoApi {

    private StudyGankIoService studyGankIoService;

    public StudyGankIoApi() {
        this.studyGankIoService = ServiceGenerator.createNoHeaderServiceFrom(StudyGankIoService.class, UrlStore.API_GANKIO);
    }

    public void getGankIoData(RestAPIObserver<GankIoDataBean> restAPIObserver,String type,int page,int pre_page) {
        studyGankIoService.getGankIoData(type,page,pre_page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }
}
