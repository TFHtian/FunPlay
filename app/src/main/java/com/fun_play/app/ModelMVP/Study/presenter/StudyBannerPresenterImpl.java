package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.ModelMVP.Study.contract.StudyBannerContract;
import com.fun_play.app.ModelMVP.Study.model.StudyBannerModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;

public class StudyBannerPresenterImpl implements StudyBannerContract.Presenter{

    private StudyBannerContract.View mView;
    private StudyBannerContract.Model mModel;

    public StudyBannerPresenterImpl(StudyBannerContract.View mView) {
        this.mView = mView;
        mModel = new StudyBannerModelImpl();
    }

    @Override
    public void getWanAndroidBanner() {
        mModel.getWanAndroidBanner(new RestAPIObserver<WanAndroidBannerBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(WanAndroidBannerBean wanAndroidBannerBean) {
                mView.WanAndroidBanner(wanAndroidBannerBean);
            }

            @Override
            public void onApiError(ApiException e) {

            }

            @Override
            public void onUnAuth(UnauthException e) {

            }

            @Override
            public void onOtherError(OtherException e) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
