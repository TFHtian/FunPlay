package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.NewsListContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.datamanager.serviceApi.Study.NewListApi;

import java.util.List;

public class NewsListModelImpl implements NewsListContract.Model {

    private NewListApi newListApi;

    public NewsListModelImpl() {
        newListApi = new NewListApi();
    }

    @Override
    public void getNewsList(String id, int page, RestAPIObserver<List<NewsDetail>> observer) {
        newListApi.getNewsList(observer,id,page);
    }
}
