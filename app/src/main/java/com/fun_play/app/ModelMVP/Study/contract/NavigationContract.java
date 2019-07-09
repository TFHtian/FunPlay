package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.NavigationBean;

public class NavigationContract {

    public interface View extends IBaseView {

        void NavigationData(NavigationBean navigationBean);
    }

    public interface Presenter extends IBasePresenter {

        void getNavigationData();
    }

    public interface Model extends IBaseModel {

        void getNavigationData(RestAPIObserver<NavigationBean> observer);
    }

}
