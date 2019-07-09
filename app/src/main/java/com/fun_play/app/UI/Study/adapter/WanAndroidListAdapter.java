package com.fun_play.app.UI.Study.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.WanAndroidItemBinding;
import com.fun_play.app.datamanager.bean.study.WanAndroidData;

public class WanAndroidListAdapter extends BaseRecyclerViewAdapter<WanAndroidData> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(parent, R.layout.wan_android_item);
    }


    private class ViewHolder extends BaseRecyclerViewHolder<WanAndroidData, WanAndroidItemBinding>{

        public ViewHolder(ViewGroup parent, int study_gank_item) {
            super(parent, study_gank_item);
        }

        @Override
        public void onBindViewHolder(WanAndroidData object, int position) {
            binding.setWanAndroidBean(object);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
