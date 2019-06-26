package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.NewsDetailContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;
import com.fun_play.app.datamanager.serviceApi.Study.NewsDetailApi;

public class NewsDetailModelImpl implements NewsDetailContract.Model {

    private NewsDetailApi newsDetailApi;

    public NewsDetailModelImpl() {
        newsDetailApi = new NewsDetailApi();
    }

    @Override
    public void getNewsDetail(String aid, RestAPIObserver<NewsArticleBean> observer) {
        newsDetailApi.getNewsDetail(observer,aid);
    }

}
