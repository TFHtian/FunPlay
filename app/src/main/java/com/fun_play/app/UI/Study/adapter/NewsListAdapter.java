package com.fun_play.app.UI.Study.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.fun_play.app.R;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewAdapter;
import com.fun_play.app.base.BaseAdapter.BaseRecyclerViewHolder;
import com.fun_play.app.databinding.NewsAdvertLongImageItemBinding;
import com.fun_play.app.databinding.NewsAdvertSlideImageItemBinding;
import com.fun_play.app.databinding.NewsAdvertTitleImageItemBinding;
import com.fun_play.app.databinding.NewsDocLongImageItemBinding;
import com.fun_play.app.databinding.NewsDocLongVideoItemBinding;
import com.fun_play.app.databinding.NewsDocSlideImageItemBinding;
import com.fun_play.app.databinding.NewsDocTitleImageItemBinding;
import com.fun_play.app.datamanager.bean.study.NewsDetail;
import com.fun_play.app.utils.GlideUtil;


public class NewsListAdapter extends BaseRecyclerViewAdapter<NewsDetail.ItemBean> {

    private NewsListListener newsListListener;

    public NewsListAdapter(NewsListListener newsListListener) {
        this.newsListListener = newsListListener;
    }

    public interface NewsListListener{
        void clickNews(String id);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        BaseRecyclerViewHolder viewHolder = null;
        switch (i){
            case NewsDetail.ItemBean.TYPE_DOC_TITLEIMG:
                viewHolder = new NewDocTitleImageViewHolder(parent, R.layout.news_doc_title_image_item);
                break;
            case NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG:
                viewHolder = new NewsDocSlideImageViewHolder(parent, R.layout.news_doc_slide_image_item);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG:
                viewHolder = new NewsAdvertImageViewHolder(parent, R.layout.news_advert_title_image_item);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG:
                viewHolder = new NewsAdvertSlideImageViewHolder(parent, R.layout.news_advert_slide_image_item);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG:
                viewHolder = new NewAdvertLongImageViewHolder(parent, R.layout.news_advert_long_image_item);
                break;
            case NewsDetail.ItemBean.TYPE_SLIDE:
                viewHolder = new NewDocLongImageViewHolder(parent, R.layout.news_doc_long_image_item);
                break;
            case NewsDetail.ItemBean.TYPE_PHVIDEO:
                viewHolder = new NewDocLongVideoViewHolder(parent, R.layout.news_doc_long_video_item);
                break;
        }
        return viewHolder;
    }

    //单张图片新闻item
    private class NewDocTitleImageViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsDocTitleImageItemBinding>{

        public NewDocTitleImageViewHolder(ViewGroup parent, int news_doc_title_image_item) {
            super(parent, news_doc_title_image_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            GlideUtil.displayGif(object.getThumbnail(), binding.ivThumb);
            binding.executePendingBindings();
            binding.linDocTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsListListener.clickNews(object.getDocumentId());
                }
            });
        }
    }

    //多张图片新闻item
    private class NewsDocSlideImageViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsDocSlideImageItemBinding>{

        public NewsDocSlideImageViewHolder(ViewGroup parent, int news_doc_slide_image_item) {
            super(parent, news_doc_slide_image_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            try {
                GlideUtil.displayGif(object.getStyle().getImages().get(0), binding.ivLeft);
                GlideUtil.displayGif(object.getStyle().getImages().get(1), binding.ivMiddle);
                GlideUtil.displayGif(object.getStyle().getImages().get(2), binding.ivRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
            binding.executePendingBindings();
            binding.linDocSlide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsListListener.clickNews(object.getDocumentId());
                }
            });
        }
    }

    //单张图片广告
    private class NewsAdvertImageViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsAdvertTitleImageItemBinding>{

        public NewsAdvertImageViewHolder(ViewGroup parent, int news_advert_title_image_item) {
            super(parent, news_advert_title_image_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            GlideUtil.displayGif(object.getThumbnail(), binding.ivThumb);
            binding.executePendingBindings();
        }
    }

    //多张图片广告item
    private class NewsAdvertSlideImageViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsAdvertSlideImageItemBinding>{

        public NewsAdvertSlideImageViewHolder(ViewGroup parent, int news_advert_slide_image_item) {
            super(parent, news_advert_slide_image_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            try {
                GlideUtil.displayGif(object.getStyle().getImages().get(0), binding.ivLeft);
                GlideUtil.displayGif(object.getStyle().getImages().get(1), binding.ivMiddle);
                GlideUtil.displayGif(object.getStyle().getImages().get(2), binding.ivRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
            binding.executePendingBindings();
        }
    }

    //长图广告
    private class NewAdvertLongImageViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsAdvertLongImageItemBinding>{

        public NewAdvertLongImageViewHolder(ViewGroup parent, int news_advert_long_image_item) {
            super(parent, news_advert_long_image_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            GlideUtil.displayGif(object.getThumbnail(), binding.ivAdvert);
            binding.executePendingBindings();
        }
    }

    //长图新闻
    private class NewDocLongImageViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsDocLongImageItemBinding>{

        public NewDocLongImageViewHolder(ViewGroup parent, int news_doc_long_image_item) {
            super(parent, news_doc_long_image_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            GlideUtil.displayGif(object.getThumbnail(), binding.ivDetail);
            binding.executePendingBindings();
        }
    }

    private class NewDocLongVideoViewHolder extends BaseRecyclerViewHolder<NewsDetail.ItemBean, NewsDocLongVideoItemBinding>{

        public NewDocLongVideoViewHolder(ViewGroup parent, int news_doc_long_video_item) {
            super(parent, news_doc_long_video_item);
        }

        @Override
        public void onBindViewHolder(NewsDetail.ItemBean object, int position) {
            binding.setNewsBean(object);
            GlideUtil.displayGif(object.getThumbnail(), binding.ivDetail);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getNewsItemType();
    }
}
