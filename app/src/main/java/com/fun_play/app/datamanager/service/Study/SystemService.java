package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.SystemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SystemService {

    @GET(UrlStore.Get_System_Data)
    Observable<SystemBean> getSystemData();

}
