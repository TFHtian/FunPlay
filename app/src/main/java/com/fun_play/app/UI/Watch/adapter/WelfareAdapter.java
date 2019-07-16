package com.fun_play.app.UI.Watch.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemWelfareBinding;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.utils.DensityUtil;

public class WelfareAdapter extends BaseRecyclerViewAdapter<GankIoDataBean.ResultBean> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_welfare);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<GankIoDataBean.ResultBean, ItemWelfareBinding> {


        ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        public void onBindViewHolder(final GankIoDataBean.ResultBean resultsBean, final int position) {

            if (position % 2 == 0) {
                DensityUtil.setViewMargin(itemView, false, 12, 6, 12, 0);
            } else {
                DensityUtil.setViewMargin(itemView, false, 6, 12, 12, 0);
            }
            binding.setBean(resultsBean);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(resultsBean, position);
                }
            });
        }
    }
}
