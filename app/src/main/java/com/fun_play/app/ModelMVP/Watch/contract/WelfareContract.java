package com.fun_play.app.ModelMVP.Watch.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;

public class WelfareContract {

    public interface View extends IBaseView {

        void WelfareData(GankIoDataBean gankIoDataBean);
    }

    public interface Presenter extends IBasePresenter {

        void getWelfareData(String id, int page, int pre_page);
    }

    public interface Model extends IBaseModel {

        void getWelfareData(String id, int page, int pre_page, RestAPIObserver<GankIoDataBean> observer);
    }

}
