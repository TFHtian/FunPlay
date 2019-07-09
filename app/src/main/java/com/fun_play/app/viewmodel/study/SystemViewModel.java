package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Study.SystemCallback;
import com.fun_play.app.ModelMVP.Study.contract.SystemContract;
import com.fun_play.app.ModelMVP.Study.presenter.SystemPresnterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.SystemItemBean;

import java.util.List;

public class SystemViewModel extends BaseViewModel implements SystemContract.View{

    private SystemContract.Presenter systemPresenter;
    private SystemCallback systemCallback;

    public SystemViewModel(@NonNull Application application) {
        super(application);
        systemPresenter = new SystemPresnterImpl(this);
    }

    public void setCallback(SystemCallback systemCallback){
        this.systemCallback = systemCallback;
    }

    public void getSystemData(){
        systemPresenter.getSystemData();
    }

    @Override
    public void SystemData(List<SystemItemBean> systemItemBeanList) {
        systemCallback.SystemData(systemItemBeanList);
    }
}
