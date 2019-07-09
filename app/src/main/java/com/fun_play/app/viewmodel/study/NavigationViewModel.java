package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Study.NavigationCallback;
import com.fun_play.app.ModelMVP.Study.contract.NavigationContract;
import com.fun_play.app.ModelMVP.Study.presenter.NavigationPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.NavigationBean;

public class NavigationViewModel extends BaseViewModel implements NavigationContract.View{

    private NavigationContract.Presenter navigationPresenter;
    private NavigationCallback navigationCallback;

    public NavigationViewModel(@NonNull Application application) {
        super(application);
        navigationPresenter = new NavigationPresenterImpl(this);
    }

    public void setCallback(NavigationCallback navigationCallback){
        this.navigationCallback = navigationCallback;
    }

    public void getNavigationData(){
        navigationPresenter.getNavigationData();
    }

    @Override
    public void NavigationData(NavigationBean navigationBean) {
        navigationCallback.NavigationData(navigationBean);
    }
}
