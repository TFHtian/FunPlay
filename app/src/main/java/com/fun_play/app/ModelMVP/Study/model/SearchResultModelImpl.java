package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.SearchResultContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;
import com.fun_play.app.datamanager.serviceApi.Study.SearchResultApi;

public class SearchResultModelImpl implements SearchResultContract.Model{

    private SearchResultApi searchResultApi;

    public SearchResultModelImpl() {
        searchResultApi = new SearchResultApi();
    }

    @Override
    public void searchResult(int p, String type,String keyWord, RestAPIObserver<GankIoDataBean> observer) {
        searchResultApi.searchResult(observer,p,type,keyWord);
    }

    @Override
    public void getHotKey(RestAPIObserver<SearchTagBean> observer) {
        searchResultApi.getHotKey(observer);
    }
}
