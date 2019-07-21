package com.fun_play.app.ModelMVP.Watch.model;

import com.fun_play.app.ModelMVP.Watch.contract.FilmDetailContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;
import com.fun_play.app.datamanager.serviceApi.Watch.FilmDetailApi;

public class FilmDetailModelImpl implements FilmDetailContract.Model {

    private FilmDetailApi filmDetailApi;

    public FilmDetailModelImpl() {
        filmDetailApi = new FilmDetailApi();
    }

    @Override
    public void GetFilmDetail(int movieId, RestAPIObserver<FilmDetailBean> observer) {
        filmDetailApi.GetFilmDetail(observer,movieId);
    }
}
