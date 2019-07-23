package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchResultService {

    @GET(UrlStore.Search_Result)
    Observable<GankIoDataBean> searchResult(@Path("p") int p, @Path("type") String type, @Path("keyWord") String keyWord);

    @GET(UrlStore.Get_Hot_Key)
    Observable<SearchTagBean> getHotKey();
}
