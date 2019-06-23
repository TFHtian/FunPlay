package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.datamanager.serviceApi.Study.StudyBannerApi;
import com.fun_play.app.ModelMVP.Study.contract.StudyBannerContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;

public class StudyBannerModelImpl implements StudyBannerContract.Model {

    private StudyBannerApi studyBannerApi;

    public StudyBannerModelImpl() {
        studyBannerApi = new StudyBannerApi();
    }

    @Override
    public void getWanAndroidBanner(RestAPIObserver<WanAndroidBannerBean> observer) {
        studyBannerApi.getWanAndroidBanner(observer);
    }
}
