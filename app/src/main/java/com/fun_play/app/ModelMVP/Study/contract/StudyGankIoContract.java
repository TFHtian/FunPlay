package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;

public class StudyGankIoContract {

    public interface View extends IBaseView {

        void GankIoData(GankIoDataBean gankIoDataBean);
    }

    public interface Presenter extends IBasePresenter {

        void getGankIoData(String type,int page,int pre_page);
    }

    public interface Model extends IBaseModel {

        void getGankIoData(String type,int page,int pre_page,RestAPIObserver<GankIoDataBean> observer);
    }

}
