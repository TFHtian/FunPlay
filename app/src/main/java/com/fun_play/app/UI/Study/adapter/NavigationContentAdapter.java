package com.fun_play.app.UI.Study.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemNavigationContentBinding;
import com.fun_play.app.datamanager.bean.study.ArticlesBean;
import com.fun_play.app.datamanager.bean.study.NavigationBean;
import com.fun_play.app.utils.CommonUtils;
import com.fun_play.app.view.WebView.WebViewActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class NavigationContentAdapter extends BaseRecyclerViewAdapter<NavigationBean.DataBean> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_navigation_content);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<NavigationBean.DataBean, ItemNavigationContentBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindViewHolder(final NavigationBean.DataBean dataBean, final int position) {
            if (dataBean != null) {
                binding.setBean(dataBean);
                showTagView(binding.tflContent, dataBean.getArticles());
            }
        }
    }

    private void showTagView(TagFlowLayout flowlayoutHot, final List<ArticlesBean> beanList) {
        flowlayoutHot.removeAllViews();
        flowlayoutHot.setAdapter(new TagAdapter<ArticlesBean>(beanList) {
            @Override
            public View getView(FlowLayout parent, int position, ArticlesBean bean) {
                TextView textView = (TextView) View.inflate(parent.getContext(), R.layout.layout_system_tag, null);
                textView.setTextColor(CommonUtils.randomColor());
                textView.setText(Html.fromHtml(bean.getTitle()));
                return textView;
            }
        });
        flowlayoutHot.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ArticlesBean bean = beanList.get(position);
                WebViewActivity.loadUrl(view.getContext(), bean.getLink(), bean.getTitle());
                return true;
            }
        });
    }

}
