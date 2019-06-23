package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.NetManager.net.UrlStore;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudyGankIoService {

    //获取干货数据接口
    @GET(UrlStore.Gtt_GankIo_Data)
    Observable<GankIoDataBean> getGankIoData(@Path("type") String type, @Path("page") int page, @Path("pre_page") int pre_page);

}
