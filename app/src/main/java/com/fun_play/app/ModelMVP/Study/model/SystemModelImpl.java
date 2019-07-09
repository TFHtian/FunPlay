package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.SystemContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.SystemBean;
import com.fun_play.app.datamanager.serviceApi.Study.SystemApi;

public class SystemModelImpl implements SystemContract.Model{

    private SystemApi systemApi;

    public SystemModelImpl() {
        systemApi = new SystemApi();
    }

    @Override
    public void getSystemData(RestAPIObserver<SystemBean> observer) {
        systemApi.getSystemData(observer);
    }
}
