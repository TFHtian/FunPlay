package com.fun_play.app.UI.Watch.adapter;

import android.view.ViewGroup;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemFilmDetailStillBinding;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;

public class FilmDetailStillAdapter extends BaseRecyclerViewAdapter<FilmDetailBean.ImageListBean> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_film_detail_still);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<FilmDetailBean.ImageListBean, ItemFilmDetailStillBinding> {

        ViewHolder(ViewGroup parent, int layout) {
            super(parent, layout);
        }

        @Override
        public void onBindViewHolder(final FilmDetailBean.ImageListBean bean, int position) {
            binding.setBean(bean);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(bean, position);
                }
            });
        }
    }
}

