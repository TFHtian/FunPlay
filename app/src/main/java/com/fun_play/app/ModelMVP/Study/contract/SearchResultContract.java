package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;

public class SearchResultContract {

    public interface View extends IBaseView {

        void SearchResult(GankIoDataBean gankIoDataBean);

        void HotKey(SearchTagBean searchTagBean);
    }

    public interface Presenter extends IBasePresenter {

        void searchResult(int p, String type, String keyWord);

        void getHotKey();
    }

    public interface Model extends IBaseModel {

        void searchResult(int p, String type, String keyWord, RestAPIObserver<GankIoDataBean> observer);

        void getHotKey(RestAPIObserver<SearchTagBean> observer);
    }
}
