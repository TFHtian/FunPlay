package com.fun_play.app.UI.Study.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.StudyGankItemBinding;
import com.fun_play.app.utils.GlideUtil;
import com.fun_play.app.view.WebView.WebViewActivity;

public class GankAndroidAdapter extends BaseRecyclerViewAdapter<GankIoDataBean.ResultBean> {

    private Activity activity;

    public GankAndroidAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(parent, R.layout.study_gank_item);
    }


    private class ViewHolder extends BaseRecyclerViewHolder<GankIoDataBean.ResultBean, StudyGankItemBinding>{

        public ViewHolder(ViewGroup parent, int study_gank_item) {
            super(parent, study_gank_item);
        }

        @Override
        public void onBindViewHolder(GankIoDataBean.ResultBean object, int position) {
            if (object.getImages() != null
                    && object.getImages().size() > 0
                    && !TextUtils.isEmpty(object.getImages().get(0))) {
                binding.ivAndroidPic.setVisibility(View.VISIBLE);
                GlideUtil.displayGif(object.getImages().get(0), binding.ivAndroidPic);
            } else {
                binding.ivAndroidPic.setVisibility(View.GONE);
            }
            binding.setResultsBean(object);
            binding.executePendingBindings();

            binding.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebViewActivity.loadUrl(activity,  object.getUrl(), object.getDesc());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
