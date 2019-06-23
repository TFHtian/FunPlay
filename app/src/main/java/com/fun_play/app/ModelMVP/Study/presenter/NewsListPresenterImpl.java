package com.fun_play.app.ModelMVP.Study.presenter;


import com.fun_play.app.ModelMVP.Study.contract.NewsListContract;
import com.fun_play.app.ModelMVP.Study.model.NewsListModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NewsDetail;

import java.util.List;



public class NewsListPresenterImpl implements NewsListContract.Presenter{

    private NewsListContract.View mView;
    private NewsListContract.Model mModel;

    public NewsListPresenterImpl(NewsListContract.View mView) {
        this.mView = mView;
        mModel = new NewsListModelImpl();
    }

    @Override
    public void getNewsList(String id, int page) {
        mModel.getNewsList(id, page, new RestAPIObserver<List<NewsDetail>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<NewsDetail> newsDetails) {
                if (newsDetails.size()>0){
                    mView.NewsList(newsDetails.get(0).getItem());
                }else {
                    mView.NewsList(null);
                }
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
