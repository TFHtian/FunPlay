package com.fun_play.app.viewmodel.watch;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Watch.FilmShowingCallback;
import com.fun_play.app.ModelMVP.Watch.contract.FilmShowingContract;
import com.fun_play.app.ModelMVP.Watch.presenter.FilmShowingPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.watch.FilmShowingBean;

public class FilmShowingViewModel extends BaseViewModel implements FilmShowingContract.View{

    private FilmShowingContract.Presenter filmShowPresenter;
    private FilmShowingCallback filmShowingCallback;

    public FilmShowingViewModel(@NonNull Application application) {
        super(application);
        filmShowPresenter = new FilmShowingPresenterImpl(this);
    }

    public void setCallback(FilmShowingCallback filmShowingCallback){
        this.filmShowingCallback = filmShowingCallback;
    }

    public void refreshFilmShowing(){
        filmShowPresenter.getFilmShowingData(561);
    }

    public void loadFilmShowing(){
        filmShowPresenter.getFilmShowingData(562);
    }

    @Override
    public void FilmShowingData(FilmShowingBean filmShowingBean) {
        if (filmShowingBean.getMs().size()>0){
            filmShowingCallback.FilmShowingData(filmShowingBean.getMs());
        }else {
            filmShowingCallback.FilmShowingData(null);
        }
    }

}
