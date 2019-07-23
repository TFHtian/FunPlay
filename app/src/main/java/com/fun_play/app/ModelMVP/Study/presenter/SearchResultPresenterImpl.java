package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.ModelMVP.Study.contract.SearchResultContract;
import com.fun_play.app.ModelMVP.Study.model.SearchResultModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;

public class SearchResultPresenterImpl implements SearchResultContract.Presenter{

    private SearchResultContract.View mView;
    private SearchResultContract.Model mModel;

    public SearchResultPresenterImpl(SearchResultContract.View mView) {
        this.mView = mView;
        mModel = new SearchResultModelImpl();
    }

    @Override
    public void searchResult(int p, String type,String keyWord) {
        mModel.searchResult(p, type, keyWord,new RestAPIObserver<GankIoDataBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(GankIoDataBean gankIoDataBean) {
                mView.SearchResult(gankIoDataBean);
            }

            @Override
            public void onApiError(ApiException e) {

            }

            @Override
            public void onUnAuth(UnauthException e) {

            }

            @Override
            public void onOtherError(OtherException e) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void getHotKey() {
        mModel.getHotKey(new RestAPIObserver<SearchTagBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(SearchTagBean searchTagBean) {
                mView.HotKey(searchTagBean);
            }

            @Override
            public void onApiError(ApiException e) {

            }

            @Override
            public void onUnAuth(UnauthException e) {

            }

            @Override
            public void onOtherError(OtherException e) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
