package com.fun_play.app.datamanager.service.Watch;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WelfareService {

    @GET(UrlStore.Get_Welfare_Data)
    Observable<GankIoDataBean> getWelfareData(@Path("type") String id, @Path("page") int page, @Path("pre_page") int pre_page);

}
