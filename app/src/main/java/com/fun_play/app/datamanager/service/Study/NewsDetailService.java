package com.fun_play.app.datamanager.service.Study;

import com.fun_play.app.NetManager.net.UrlStore;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsDetailService {

    @GET(UrlStore.Get_News_Detail)
    Observable<NewsArticleBean> getNewsDetail(@Query("aid") String aid);

}
