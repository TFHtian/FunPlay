package com.fun_play.app.ModelCallback.Study;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;

public interface SearchResultCallback {

    void SearchResult(GankIoDataBean gankIoDataBean);

    void HotKey(SearchTagBean searchTagBean);
}
