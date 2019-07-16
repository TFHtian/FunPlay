package com.fun_play.app.datamanager.bean.watch;

import java.util.List;

public class FilmShowingBean {

    private int totalComingMovie;
    private List<FilmItemBean> ms;

    public int getTotalComingMovie() {
        return totalComingMovie;
    }

    public void setTotalComingMovie(int totalComingMovie) {
        this.totalComingMovie = totalComingMovie;
    }

    public List<FilmItemBean> getMs() {
        return ms;
    }

    public void setMs(List<FilmItemBean> ms) {
        this.ms = ms;
    }
}
