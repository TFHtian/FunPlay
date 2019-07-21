package com.fun_play.app.datamanager.service.Watch;


import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmDetailService {

    @GET(UrlStore.Get_Film_Detail)
    Observable<FilmDetailBean> GetFilmDetail(@Query("movieId") int movieId);

}
