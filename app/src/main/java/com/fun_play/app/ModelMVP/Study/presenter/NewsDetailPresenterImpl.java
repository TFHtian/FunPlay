package com.fun_play.app.ModelMVP.Study.presenter;

import com.fun_play.app.ModelMVP.Study.contract.NewsDetailContract;
import com.fun_play.app.ModelMVP.Study.model.NewsDetailModelImpl;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;

public class NewsDetailPresenterImpl implements NewsDetailContract.Presenter{

    private NewsDetailContract.View mView;
    private NewsDetailContract.Model mModel;

    public NewsDetailPresenterImpl(NewsDetailContract.View mView) {
        this.mView = mView;
        mModel = new NewsDetailModelImpl();
    }

    @Override
    public void getNewsDetail(String aid) {
        mModel.getNewsDetail(aid, new RestAPIObserver<NewsArticleBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(NewsArticleBean newsArticleBean) {
                mView.NewsDetail(newsArticleBean);
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
