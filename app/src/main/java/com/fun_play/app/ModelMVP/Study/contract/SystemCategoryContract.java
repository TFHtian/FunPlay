package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.SystemListBean;

public class SystemCategoryContract {

    public interface View extends IBaseView {

        void SystemList(SystemListBean systemListBean);
    }

    public interface Presenter extends IBasePresenter {

        void getSystemList(int page, Integer cid);
    }

    public interface Model extends IBaseModel {

        void getSystemList(int page, Integer cid, RestAPIObserver<SystemListBean> observer);
    }

}
