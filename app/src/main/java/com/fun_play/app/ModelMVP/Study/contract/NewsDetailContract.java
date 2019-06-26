package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;
import com.fun_play.app.datamanager.bean.study.NewsDetail;

import java.util.List;

public class NewsDetailContract {

    public interface View extends IBaseView {

        void NewsDetail(NewsArticleBean newsArticleBean);
    }

    public interface Presenter extends IBasePresenter {

        void getNewsDetail(String aid);
    }

    public interface Model extends IBaseModel {

        void getNewsDetail(String aid, RestAPIObserver<NewsArticleBean> observer);
    }


}
