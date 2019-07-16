package com.fun_play.app.ModelMVP.Watch.model;

import com.fun_play.app.ModelMVP.Watch.contract.WelfareContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.serviceApi.Watch.WelfareApi;

public class WelfareModelImpl implements WelfareContract.Model{

    private WelfareApi welfareApi;

    public WelfareModelImpl() {
        welfareApi = new WelfareApi();
    }

    @Override
    public void getWelfareData(String id, int page, int pre_page, RestAPIObserver<GankIoDataBean> observer) {
        welfareApi.getWelfareData(observer,id,page,pre_page);
    }
}
