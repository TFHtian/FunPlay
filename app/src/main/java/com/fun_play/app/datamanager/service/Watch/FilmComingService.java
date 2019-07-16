package com.fun_play.app.datamanager.service.Watch;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmComingService {

    @GET(UrlStore.Get_Film_Coming_Data)
    Observable<ComingFilmBean> getFilmComingData(@Query("locationId") int locationId);
}
