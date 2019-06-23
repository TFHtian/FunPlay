package com.fun_play.app.ModelCallback.Study;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.datamanager.bean.study.WanAndroidBannerBean;

import java.util.List;

public interface WanAndroidBannerCallback {

    void WanAndroidBanner(WanAndroidBannerBean wanAndroidBannerBean);

    void ShowLoading(Boolean isShowLoading);

    void NewsList(List<NewsDetail.ItemBean> itemBeanList);
}
