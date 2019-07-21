package com.fun_play.app.ModelMVP.Watch.presenter;

import com.fun_play.app.ModelMVP.Watch.contract.FilmDetailContract;
import com.fun_play.app.ModelMVP.Watch.model.FilmDetailModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;

public class FilmDetailPresenterImpl implements FilmDetailContract.Presenter{

    private FilmDetailContract.View mView;
    private FilmDetailContract.Model mModel;

    public FilmDetailPresenterImpl(FilmDetailContract.View mView) {
        this.mView = mView;
        mModel = new FilmDetailModelImpl();
    }

    @Override
    public void GetFilmDetail(int movieId) {
        mModel.GetFilmDetail(movieId, new RestAPIObserver<FilmDetailBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(FilmDetailBean filmDetailBean) {
                mView.FilmDetail(filmDetailBean);
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
