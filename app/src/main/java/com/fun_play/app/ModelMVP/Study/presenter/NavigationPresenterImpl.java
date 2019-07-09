package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.ModelMVP.Study.contract.NavigationContract;
import com.fun_play.app.ModelMVP.Study.model.NavigationModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NavigationBean;

public class NavigationPresenterImpl implements NavigationContract.Presenter{

    private NavigationContract.View mView;
    private NavigationContract.Model mModel;

    public NavigationPresenterImpl(NavigationContract.View mView) {
        this.mView = mView;
        mModel = new NavigationModelImpl();
    }

    @Override
    public void getNavigationData() {
        mModel.getNavigationData(new RestAPIObserver<NavigationBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(NavigationBean navigationBean) {
                mView.NavigationData(navigationBean);
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
