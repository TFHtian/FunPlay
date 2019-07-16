package com.fun_play.app.ModelCallback.Watch;

import com.fun_play.app.datamanager.bean.watch.FilmItemBean;

import java.util.List;

public interface FilmShowingCallback {

    void FilmShowingData(List<FilmItemBean> filmItemBeanList);
}
