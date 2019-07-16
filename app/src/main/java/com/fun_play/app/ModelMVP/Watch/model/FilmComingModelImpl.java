package com.fun_play.app.ModelMVP.Watch.model;

import com.fun_play.app.ModelMVP.Watch.contract.FilmComingContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;
import com.fun_play.app.datamanager.serviceApi.Watch.FilmComingApi;

public class FilmComingModelImpl implements FilmComingContract.Model{

    private FilmComingApi filmComingApi;

    public FilmComingModelImpl() {
        filmComingApi = new FilmComingApi();
    }

    @Override
    public void getFilmComingData(int locationId, RestAPIObserver<ComingFilmBean> observer) {
        filmComingApi.getFilmComingData(observer,locationId);
    }
}
