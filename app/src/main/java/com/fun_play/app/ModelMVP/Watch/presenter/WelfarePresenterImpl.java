package com.fun_play.app.ModelMVP.Watch.presenter;

import com.fun_play.app.ModelMVP.Watch.contract.WelfareContract;
import com.fun_play.app.ModelMVP.Watch.model.WelfareModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;

public class WelfarePresenterImpl implements WelfareContract.Presenter{

    private WelfareContract.View mView;
    private WelfareContract.Model mModel;

    public WelfarePresenterImpl(WelfareContract.View mView) {
        this.mView = mView;
        mModel = new WelfareModelImpl();
    }

    @Override
    public void getWelfareData(String id, int page, int pre_page) {
        mModel.getWelfareData(id, page, pre_page, new RestAPIObserver<GankIoDataBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(GankIoDataBean gankIoDataBean) {
                mView.WelfareData(gankIoDataBean);
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
