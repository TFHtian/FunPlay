package com.fun_play.app.datamanager.service.Watch;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.watch.FilmShowingBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmShowingService {

    @GET(UrlStore.Get_Film_Showing_Data)
    Observable<FilmShowingBean> getFilmShowingData(@Query("locationId") int locationId);

}
