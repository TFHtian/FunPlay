package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.serviceApi.Study.StudyGankIoApi;
import com.fun_play.app.ModelMVP.Study.contract.StudyGankIoContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;

public class StudyGankIoModelImpl implements StudyGankIoContract.Model {

    private StudyGankIoApi studyGankIoApi;

    public StudyGankIoModelImpl() {
        studyGankIoApi = new StudyGankIoApi();
    }

    @Override
    public void getGankIoData(String type, int page, int pre_page, RestAPIObserver<GankIoDataBean> observer) {
        studyGankIoApi.getGankIoData(observer,type,page,pre_page);
    }
}
