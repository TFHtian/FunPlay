package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.ModelMVP.Study.contract.StudyGankIoContract;
import com.fun_play.app.ModelMVP.Study.model.StudyGankIoModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;

public class StudyGankIoPresenterImpl implements StudyGankIoContract.Presenter{

    private StudyGankIoContract.View mView;
    private StudyGankIoContract.Model mModel;

    public StudyGankIoPresenterImpl(StudyGankIoContract.View mView) {
        this.mView = mView;
        mModel = new StudyGankIoModelImpl();
    }

    @Override
    public void getGankIoData(String type, int page, int pre_page) {
        mModel.getGankIoData(type, page, pre_page, new RestAPIObserver<GankIoDataBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(GankIoDataBean gankIoDataBean) {
                mView.GankIoData(gankIoDataBean);
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
