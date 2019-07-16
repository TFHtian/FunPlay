package com.fun_play.app.datamanager.serviceApi.Watch;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.FilmShowingBean;
import com.fun_play.app.datamanager.service.Watch.FilmShowingService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FilmShowingApi {

    private FilmShowingService filmShowingService;

    public FilmShowingApi() {
        this.filmShowingService = ServiceGenerator.createNoHeaderServiceFrom(FilmShowingService.class, UrlStore.API_FILM);
    }

    public void getFilmShowingData(RestAPIObserver<FilmShowingBean> restAPIObserver, int locationId) {
        filmShowingService.getFilmShowingData(locationId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
