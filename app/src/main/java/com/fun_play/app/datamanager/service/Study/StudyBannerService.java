package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.NetManager.net.UrlStore;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface StudyBannerService {

    //获取玩安卓轮播图
    @GET(UrlStore.WAN_ANDROID_BANNER)
    Observable<WanAndroidBannerBean> getWanAndroidBanner();

}
