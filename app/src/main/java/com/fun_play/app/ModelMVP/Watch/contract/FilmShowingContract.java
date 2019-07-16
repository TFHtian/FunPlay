package com.fun_play.app.ModelMVP.Watch.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.watch.FilmShowingBean;

public class FilmShowingContract {

    public interface View extends IBaseView {

        void FilmShowingData(FilmShowingBean filmShowingBean);
    }

    public interface Presenter extends IBasePresenter {

        void getFilmShowingData(int locationId);
    }

    public interface Model extends IBaseModel {

        void getFilmShowingData(int locationId, RestAPIObserver<FilmShowingBean> observer);
    }

}
