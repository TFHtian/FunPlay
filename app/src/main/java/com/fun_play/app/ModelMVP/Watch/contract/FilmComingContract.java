package com.fun_play.app.ModelMVP.Watch.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;

public class FilmComingContract {

    public interface View extends IBaseView {

        void FilmComingData(ComingFilmBean comingFilmBean);
    }

    public interface Presenter extends IBasePresenter {

        void getFilmComingData(int locationId);
    }

    public interface Model extends IBaseModel {

        void getFilmComingData(int locationId, RestAPIObserver<ComingFilmBean> observer);
    }

}
