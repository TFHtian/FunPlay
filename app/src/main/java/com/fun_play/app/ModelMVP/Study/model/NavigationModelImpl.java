package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.NavigationContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.NavigationBean;
import com.fun_play.app.datamanager.serviceApi.Study.NavigationApi;

public class NavigationModelImpl implements NavigationContract.Model{

    private NavigationApi navigationApi;

    public NavigationModelImpl() {
        navigationApi = new NavigationApi();
    }

    @Override
    public void getNavigationData(RestAPIObserver<NavigationBean> observer) {
        navigationApi.getNavigationData(observer);
    }
}
