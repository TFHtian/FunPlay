package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.SystemBean;
import com.fun_play.app.datamanager.bean.study.SystemItemBean;

import java.util.List;

public class SystemContract {

    public interface View extends IBaseView {

        void SystemData(List<SystemItemBean> systemItemBeanList);
    }

    public interface Presenter extends IBasePresenter {

        void getSystemData();
    }

    public interface Model extends IBaseModel {

        void getSystemData(RestAPIObserver<SystemBean> observer);
    }

}
