package com.fun_play.app.viewmodel.study;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fun_play.app.ModelCallback.Study.SystemCategoryCallback;
import com.fun_play.app.ModelMVP.Study.contract.SystemCategoryContract;
import com.fun_play.app.ModelMVP.Study.presenter.SystemCategoryPresenterImpl;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.SystemListBean;

public class SystemCategoryViewModel extends BaseViewModel implements SystemCategoryContract.View{

    private SystemCategoryContract.Presenter systemCategoryPresenter;
    private SystemCategoryCallback systemCategoryCallback;
    private int page = 0 ;

    public SystemCategoryViewModel(@NonNull Application application) {
        super(application);
        systemCategoryPresenter = new SystemCategoryPresenterImpl(this);
    }

    public void setCallback(SystemCategoryCallback systemCategoryCallback){
        this.systemCategoryCallback = systemCategoryCallback;
    }

    public void refreshSystem(int cid){
        page = 0;
        systemCategoryPresenter.getSystemList(page,cid);
    }

    public void loadSystem(int cid){
        page ++;
        systemCategoryPresenter.getSystemList(page,cid);
    }

    @Override
    public void SystemList(SystemListBean systemListBean) {
        systemCategoryCallback.SystemList(systemListBean.getData().getDatas());
    }
}
