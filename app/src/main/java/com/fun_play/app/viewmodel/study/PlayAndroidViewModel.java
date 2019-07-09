package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Study.WanAndroidCallback;
import com.fun_play.app.ModelMVP.Study.contract.WanAndroidListContract;
import com.fun_play.app.ModelMVP.Study.presenter.WanAndroidListPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.WanAndroidData;

import java.util.List;

public class PlayAndroidViewModel extends BaseViewModel implements WanAndroidListContract.View{

    private WanAndroidListContract.Presenter wanAndroidPresenter;
    private WanAndroidCallback wanAndroidCallback;

    private int cid = 0;
    public PlayAndroidViewModel(@NonNull Application application) {
        super(application);
        wanAndroidPresenter = new WanAndroidListPresenterImpl(this);
    }

    public void setCallback(WanAndroidCallback wanAndroidCallback){
        this.wanAndroidCallback = wanAndroidCallback;
    }

    public void refreshData(){
        cid = 0;
        wanAndroidPresenter.getWanAndroidData(cid);
    }

    public void loadData(){
        cid++;
        wanAndroidPresenter.getWanAndroidData(cid);
    }

    @Override
    public void WanAndroidData(List<WanAndroidData> wanAndroidDataList) {
        wanAndroidCallback.WanAndroidData(wanAndroidDataList);
    }
}






