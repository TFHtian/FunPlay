package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.ModelMVP.Study.contract.SystemContract;
import com.fun_play.app.ModelMVP.Study.model.SystemModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.SystemBean;

public class SystemPresnterImpl implements SystemContract.Presenter{

    private SystemContract.View mView;
    private SystemContract.Model mModel;

    public SystemPresnterImpl(SystemContract.View mView) {
        this.mView = mView;
        mModel = new SystemModelImpl();
    }

    @Override
    public void getSystemData() {
        mModel.getSystemData(new RestAPIObserver<SystemBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(SystemBean systemBean) {
                mView.SystemData(systemBean.getData());
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
