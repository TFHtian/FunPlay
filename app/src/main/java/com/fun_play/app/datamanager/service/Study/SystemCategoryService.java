package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.SystemListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SystemCategoryService {

    @GET(UrlStore.GetS_System_List)
    Observable<SystemListBean> getSystemList(@Path("page") int page, @Query("cid") Integer cid);

}
