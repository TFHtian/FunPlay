package com.fun_play.app.ModelMVP.Watch.presenter;

import com.fun_play.app.ModelMVP.Watch.contract.FilmComingContract;
import com.fun_play.app.ModelMVP.Watch.model.FilmComingModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;

public class FilmComingPresenterImpl implements FilmComingContract.Presenter{

    private FilmComingContract.View mView;
    private FilmComingContract.Model mModel;

    public FilmComingPresenterImpl(FilmComingContract.View mView) {
        this.mView = mView;
        mModel = new FilmComingModelImpl();
    }

    @Override
    public void getFilmComingData(int locationId) {
        mModel.getFilmComingData(locationId, new RestAPIObserver<ComingFilmBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(ComingFilmBean comingFilmBean) {
                mView.FilmComingData(comingFilmBean);
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
