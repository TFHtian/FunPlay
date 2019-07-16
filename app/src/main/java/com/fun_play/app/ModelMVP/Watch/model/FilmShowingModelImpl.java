package com.fun_play.app.ModelMVP.Watch.model;

import com.fun_play.app.ModelMVP.Watch.contract.FilmShowingContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.FilmShowingBean;
import com.fun_play.app.datamanager.serviceApi.Watch.FilmShowingApi;

public class FilmShowingModelImpl implements FilmShowingContract.Model{

    private FilmShowingApi filmShowingApi;

    public FilmShowingModelImpl() {
        filmShowingApi = new FilmShowingApi();
    }

    @Override
    public void getFilmShowingData(int locationId, RestAPIObserver<FilmShowingBean> observer) {
        filmShowingApi.getFilmShowingData(observer,locationId);
    }
}
