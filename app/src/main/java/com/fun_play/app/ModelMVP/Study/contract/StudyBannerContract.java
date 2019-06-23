package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;

public class StudyBannerContract {

    public interface View extends IBaseView {

        void WanAndroidBanner(WanAndroidBannerBean wanAndroidBannerBean);
    }

    public interface Presenter extends IBasePresenter {

        void getWanAndroidBanner();
    }

    public interface Model extends IBaseModel {

        void getWanAndroidBanner(RestAPIObserver<WanAndroidBannerBean> observer);
    }

}
