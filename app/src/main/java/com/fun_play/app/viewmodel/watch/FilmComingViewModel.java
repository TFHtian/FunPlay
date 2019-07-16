package com.fun_play.app.viewmodel.watch;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Watch.FilmComingCallback;
import com.fun_play.app.ModelMVP.Watch.contract.FilmComingContract;
import com.fun_play.app.ModelMVP.Watch.presenter.FilmComingPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.watch.ComingFilmBean;

public class FilmComingViewModel extends BaseViewModel implements FilmComingContract.View{

    private FilmComingContract.Presenter filmComingPresenter;
    private FilmComingCallback filmComingCallback;

    public FilmComingViewModel(@NonNull Application application) {
        super(application);
        filmComingPresenter = new FilmComingPresenterImpl(this);
    }

    public void setCallback(FilmComingCallback filmComingCallback){
        this.filmComingCallback = filmComingCallback;
    }

    public void refreshFilmComing(){
        filmComingPresenter.getFilmComingData(561);
    }

    public void loadFilmComing(){
        filmComingPresenter.getFilmComingData(561);
    }

    @Override
    public void FilmComingData(ComingFilmBean comingFilmBean) {
        filmComingCallback.FilmComingData(comingFilmBean);
    }
}
