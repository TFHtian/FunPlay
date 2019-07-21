package com.fun_play.app.datamanager.serviceApi.Watch;

import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;
import com.fun_play.app.datamanager.service.Watch.FilmDetailService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FilmDetailApi {

    private FilmDetailService filmDetailService;

    public FilmDetailApi() {
        this.filmDetailService = ServiceGenerator.createNoHeaderServiceFrom(FilmDetailService.class, UrlStore.API_MTIME_TICKET);
    }

    public void GetFilmDetail(RestAPIObserver<FilmDetailBean> restAPIObserver, int movieId) {
        filmDetailService.GetFilmDetail(movieId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restAPIObserver);
    }

}
