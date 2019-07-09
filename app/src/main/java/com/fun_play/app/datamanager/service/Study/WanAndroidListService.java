package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.WanAndroidBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidListService {

    @GET(UrlStore.WAN_ANDROID_LIST)
    Observable<WanAndroidBean> getWanAndroidData(@Path("cid") int cid);

}
