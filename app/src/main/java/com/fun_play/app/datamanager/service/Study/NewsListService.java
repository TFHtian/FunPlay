package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.NewsDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsListService {

    //获取干货数据接口
    @GET(UrlStore.Get_News_List_Data)
    Observable<List<NewsDetail>> getNewsList(@Query("id") String id, @Query("page") int page);

}
