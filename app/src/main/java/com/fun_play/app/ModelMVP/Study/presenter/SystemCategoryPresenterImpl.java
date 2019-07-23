package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.ModelMVP.Study.contract.SystemCategoryContract;
import com.fun_play.app.ModelMVP.Study.model.SystemCategoryModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.SystemListBean;

public class SystemCategoryPresenterImpl implements SystemCategoryContract.Presenter{

    private SystemCategoryContract.View mView;
    private SystemCategoryContract.Model mModel;

    public SystemCategoryPresenterImpl(SystemCategoryContract.View mView) {
        this.mView = mView;
        mModel = new SystemCategoryModelImpl();
    }

    @Override
    public void getSystemList(int page, Integer cid) {
        mModel.getSystemList(page, cid, new RestAPIObserver<SystemListBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(SystemListBean systemListBean) {
                mView.SystemList(systemListBean);
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
