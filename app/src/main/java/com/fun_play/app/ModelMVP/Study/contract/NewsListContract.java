package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.NewsDetail;

import java.util.List;

public class NewsListContract {

    public interface View extends IBaseView {

        void NewsList(List<NewsDetail.ItemBean> itemBeanList);
    }

    public interface Presenter extends IBasePresenter {

        void getNewsList(String id, int page);
    }

    public interface Model extends IBaseModel {

        void getNewsList(String id, int page, RestAPIObserver<List<NewsDetail>> observer);
    }

}
