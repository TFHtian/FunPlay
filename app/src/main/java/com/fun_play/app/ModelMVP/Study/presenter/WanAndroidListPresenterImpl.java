package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.ModelMVP.Study.contract.WanAndroidListContract;
import com.fun_play.app.ModelMVP.Study.model.WanAndroidListModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.WanAndroidBean;

public class WanAndroidListPresenterImpl implements WanAndroidListContract.Presenter{

    private WanAndroidListContract.View mView;
    private WanAndroidListContract.Model mModel;

    public WanAndroidListPresenterImpl(WanAndroidListContract.View mView) {
        this.mView = mView;
        mModel = new WanAndroidListModelImpl();
    }

    @Override
    public void getWanAndroidData(int cid) {
        mModel.getWanAndroidData(cid, new RestAPIObserver<WanAndroidBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(WanAndroidBean wanAndroidBean) {
                mView.WanAndroidData(wanAndroidBean.getData().getDatas());
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
