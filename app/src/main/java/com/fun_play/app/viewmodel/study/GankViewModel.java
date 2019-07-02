package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Study.StudyGankCallback;
import com.fun_play.app.ModelMVP.Study.contract.StudyGankIoContract;
import com.fun_play.app.ModelMVP.Study.presenter.StudyGankIoPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;

public class GankViewModel extends BaseViewModel implements StudyGankIoContract.View{

    private StudyGankIoContract.Presenter studyGankPresenter;
    private StudyGankCallback studyGankCallback;
    private int page = 1;
    private int pre_page = 15;

    public GankViewModel(@NonNull Application application) {
        super(application);
        studyGankPresenter = new StudyGankIoPresenterImpl(this);
    }

    public void setCallback(StudyGankCallback studyGankCallback){
        this.studyGankCallback = studyGankCallback;
    }

    public void refreshGank(String type){
        page = 1;
        studyGankPresenter.getGankIoData(type,page,pre_page);
    }

    public void loadGank(String type){
        page ++;
        studyGankPresenter.getGankIoData(type,page,pre_page);
    }

    @Override
    public void GankIoData(GankIoDataBean gankIoDataBean) {
        studyGankCallback.GankIoData(gankIoDataBean);
    }

}
