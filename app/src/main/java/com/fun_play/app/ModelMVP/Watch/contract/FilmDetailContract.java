package com.fun_play.app.ModelMVP.Watch.contract;

import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.base.baseMVP.model.IBaseModel;
import com.fun_play.app.base.baseMVP.presenter.IBasePresenter;
import com.fun_play.app.base.baseMVP.view.IBaseView;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;

public class FilmDetailContract {

    public interface View extends IBaseView {

        void FilmDetail(FilmDetailBean filmDetailBean);
    }

    public interface Presenter extends IBasePresenter {

        void GetFilmDetail(int movieId);
    }

    public interface Model extends IBaseModel {

        void GetFilmDetail(int movieId, RestAPIObserver<FilmDetailBean> observer);
    }

}
