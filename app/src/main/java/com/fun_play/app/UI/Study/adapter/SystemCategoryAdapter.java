package com.fun_play.app.UI.Study.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemSystemCategoryBinding;
import com.fun_play.app.datamanager.bean.study.ArticlesBean;
import com.fun_play.app.view.WebView.WebViewActivity;

public class SystemCategoryAdapter extends BaseRecyclerViewAdapter<ArticlesBean> {

    private Activity activity;

    public SystemCategoryAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(parent, R.layout.item_system_category);
    }


    private class ViewHolder extends BaseRecyclerViewHolder<ArticlesBean, ItemSystemCategoryBinding> {

        public ViewHolder(ViewGroup parent, int study_gank_item) {
            super(parent, study_gank_item);
        }

        @Override
        public void onBindViewHolder(ArticlesBean object, int position) {
            binding.setBean(object);
            binding.executePendingBindings();
            binding.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebViewActivity.loadUrl(activity, object.getLink(), object.getTitle());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
