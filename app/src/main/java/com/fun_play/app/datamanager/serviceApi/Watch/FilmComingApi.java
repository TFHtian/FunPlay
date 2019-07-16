package com.fun_play.app.datamanager.serviceApi.Watch;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;
import com.fun_play.app.datamanager.service.Watch.FilmComingService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FilmComingApi {

    private FilmComingService filmComingService;

    public FilmComingApi() {
        this.filmComingService = ServiceGenerator.createNoHeaderServiceFrom(FilmComingService.class, UrlStore.API_FILM);
    }

    public void getFilmComingData(RestAPIObserver<ComingFilmBean> restAPIObserver, int locationId) {
        filmComingService.getFilmComingData(locationId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }
}
