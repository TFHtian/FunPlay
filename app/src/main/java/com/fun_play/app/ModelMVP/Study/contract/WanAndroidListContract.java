package com.fun_play.app.ModelMVP.Study.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.study.WanAndroidBean;
import com.fun_play.app.datamanager.bean.study.WanAndroidData;

import java.util.List;

public class WanAndroidListContract {

    public interface View extends IBaseView {

        void WanAndroidData(List<WanAndroidData> wanAndroidDataList);
    }

    public interface Presenter extends IBasePresenter {

        void getWanAndroidData(int cid);
    }

    public interface Model extends IBaseModel {

        void getWanAndroidData(int cid, RestAPIObserver<WanAndroidBean> observer);
    }

}
