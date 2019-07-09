package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.NavigationBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NavigationService {

    @GET(UrlStore.Get_Navigation_Data)
    Observable<NavigationBean> getNavigationData();

}
