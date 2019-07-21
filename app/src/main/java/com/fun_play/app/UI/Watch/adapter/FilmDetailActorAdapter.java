package com.fun_play.app.UI.Watch.adapter;

import android.view.ViewGroup;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemFilmDetailActorBinding;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;

public class FilmDetailActorAdapter extends BaseRecyclerViewAdapter<FilmDetailBean.ActorsBean> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_film_detail_actor);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<FilmDetailBean.ActorsBean, ItemFilmDetailActorBinding> {

        ViewHolder(ViewGroup parent, int layout) {
            super(parent, layout);
        }

        @Override
        public void onBindViewHolder(final FilmDetailBean.ActorsBean bean, int position) {
            binding.setPersonBean(bean);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(bean, position);
                }
            });
        }
    }
}
