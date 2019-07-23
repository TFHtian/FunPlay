package com.fun_play.app.UI.Study.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.ItemSystemBinding;
import com.fun_play.app.datamanager.bean.study.SystemItemBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class SystemAdapter extends BaseRecyclerViewAdapter<SystemItemBean> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_system);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<SystemItemBean, ItemSystemBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindViewHolder(final SystemItemBean dataBean, final int position) {
            if (dataBean != null) {
                binding.setBean(dataBean);
                List<SystemItemBean.ChildrenBean> children = dataBean.getChildren();
                if (children != null && children.size() > 0) {
                    showSystemView(binding.tabSystem, children, dataBean);
                }
            }
        }
    }

        private void showSystemView(TagFlowLayout flowlayoutHot, final List<SystemItemBean.ChildrenBean> children, SystemItemBean dataBean) {
        flowlayoutHot.setAdapter(new TagAdapter<SystemItemBean.ChildrenBean>(children) {
            @Override
            public View getView(FlowLayout parent, int position, SystemItemBean.ChildrenBean o) {
                TextView textView = (TextView) View.inflate(parent.getContext(), R.layout.layout_system_tag, null);
                textView.setText(Html.fromHtml(o.getName()));
                return textView;
            }
        });
            flowlayoutHot.setOnTagClickListener((view, position, parent) -> {
                SystemItemBean.ChildrenBean childrenBean = children.get(position);
                listener.onClick(dataBean,childrenBean.getId());
                return true;
            });
    }

    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(SystemItemBean bean, int cid);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

}
