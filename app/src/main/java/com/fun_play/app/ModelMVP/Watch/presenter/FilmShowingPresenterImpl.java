package com.fun_play.app.ModelMVP.Watch.presenter;

import com.fun_play.app.ModelMVP.Watch.contract.FilmShowingContract;
import com.fun_play.app.ModelMVP.Watch.model.FilmShowingModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.FilmShowingBean;

public class FilmShowingPresenterImpl implements FilmShowingContract.Presenter{

    private FilmShowingContract.View mView;
    private FilmShowingContract.Model mModel;

    public FilmShowingPresenterImpl(FilmShowingContract.View mView) {
        this.mView = mView;
        mModel = new FilmShowingModelImpl();
    }

    @Override
    public void getFilmShowingData(int locationId) {
        mModel.getFilmShowingData(locationId, new RestAPIObserver<FilmShowingBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(FilmShowingBean filmShowingBean) {
                mView.FilmShowingData(filmShowingBean);
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
