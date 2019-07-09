package com.fun_play.app.UI.Study.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemNavigationTitleBinding;
import com.fun_play.app.datamanager.bean.study.NavigationBean;


public class NavigationTitleAdapter extends BaseRecyclerViewAdapter<NavigationBean.DataBean> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_navigation_title);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<NavigationBean.DataBean, ItemNavigationTitleBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindViewHolder(final NavigationBean.DataBean dataBean, final int position) {
            if (dataBean != null) {
                binding.tvTitle.setSelected(dataBean.isSelected());
                binding.setBean(dataBean);
                binding.tvTitle.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onSelected(position);
                    }
                });
            }
        }
    }

    private OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void onSelected(int position);
    }

}
