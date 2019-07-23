package com.fun_play.app.ModelMVP.Study.model;

import com.fun_play.app.ModelMVP.Study.contract.SystemCategoryContract;
import com.fun_play.app.NetManager.net.observer.common.RestAPIObserver;
import com.fun_play.app.datamanager.bean.study.SystemListBean;
import com.fun_play.app.datamanager.serviceApi.Study.SystemCategoryApi;

public class SystemCategoryModelImpl implements SystemCategoryContract.Model{

    private SystemCategoryApi systemCategoryApi;

    public SystemCategoryModelImpl() {
        systemCategoryApi = new SystemCategoryApi();
    }

    @Override
    public void getSystemList(int page, Integer cid, RestAPIObserver<SystemListBean> observer) {
        systemCategoryApi.getSystemList(observer,page,cid);
    }
}
