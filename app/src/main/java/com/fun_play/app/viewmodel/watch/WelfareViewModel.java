package com.fun_play.app.viewmodel.watch;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Watch.WelfareCallback;
import com.fun_play.app.ModelMVP.Watch.contract.WelfareContract;
import com.fun_play.app.ModelMVP.Watch.presenter.WelfarePresenterImpl;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;

public class WelfareViewModel extends BaseViewModel implements WelfareContract.View{

    private int page = 1;
    private int pre_page = 20;
    private WelfareContract.Presenter welfarePresenter;
    private WelfareCallback welfareCallback;

    public WelfareViewModel(@NonNull Application application) {
        super(application);
        welfarePresenter = new WelfarePresenterImpl(this);
    }

    public void setCallback(WelfareCallback welfareCallback){
        this.welfareCallback = welfareCallback;
    }


    public void refreshWelfare(){
        page = 1;
        welfarePresenter.getWelfareData(Constant.Welfare,page,pre_page);
    }

    public void laodWelfare(){
        page++;
        welfarePresenter.getWelfareData(Constant.Welfare,page,pre_page);
    }

    @Override
    public void WelfareData(GankIoDataBean gankIoDataBean) {
        welfareCallback.WelfareData(gankIoDataBean);
    }
}
