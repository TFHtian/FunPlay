package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.WanAndroidListContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.WanAndroidBean;
import com.fun_play.app.datamanager.serviceApi.Study.WanAndroidListApi;

public class WanAndroidListModelImpl implements WanAndroidListContract.Model {

    private WanAndroidListApi wanAndroidListApi;

    public WanAndroidListModelImpl() {
        wanAndroidListApi = new WanAndroidListApi();
    }

    @Override
    public void getWanAndroidData(int cid, RestAPIObserver<WanAndroidBean> observer) {
        wanAndroidListApi.getWanAndroidData(observer,cid);
    }
}
