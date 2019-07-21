package com.fun_play.app.view.PicturePreview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fun_play.app.R;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityPicturePreviewBinding;
import com.fun_play.app.viewmodel.None.NoViewModel;

import com.github.chrisbanes.photoview.PhotoView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;


public class PicturePreviewActivity extends BaseActivity<NoViewModel,ActivityPicturePreviewBinding> {

    private List<String> images = new ArrayList<>();
    private SimpleFragmentAdapter adapter;
    private LayoutInflater inflater;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        initData();
        initView();
    }

    public void initData(){
        position = getIntent().getIntExtra(Constant.Position,0);
        images = getIntent().getStringArrayListExtra(Constant.PictureList);
    }

    public void initView(){
        showContentView();
        isHideToolBar(false);
        setToolBarBackground(R.color.image_overlay_top);
        inflater = LayoutInflater.from(this);
        initViewPageAdapterData();
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.image_overlay_top),0);
    }

    private void initViewPageAdapterData() {
        String topTitle = position + 1 + "/" + images.size();
        setCenterTitle(topTitle);
        adapter = new SimpleFragmentAdapter();
        bindingView.previewPager.setAdapter(adapter);
        bindingView.previewPager.setCurrentItem(position);
        bindingView.previewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String topTitle = position + 1 + "/" + images.size();
                setCenterTitle(topTitle);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public class SimpleFragmentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View contentView = inflater.inflate(R.layout.picture_image_preview, container, false);
            final PhotoView zoomImageView = contentView.findViewById(R.id.zoom_image_view);
            final ProgressBar progressBar = contentView.findViewById(R.id.loading);
            String imageUrl = images.get(position);

            progressBar.setVisibility(View.VISIBLE);
            progressBar.setClickable(false);

            Glide.with(PicturePreviewActivity.this).load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade(700))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            int height = resource.getIntrinsicHeight();
                            int wHeight = getWindowManager().getDefaultDisplay().getHeight();
                            if (height < wHeight) {
                                zoomImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            } else {
                                zoomImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                            return false;
                        }
                    }).into(zoomImageView);

            (container).addView(contentView, 0);
            return contentView;
        }
    }


}
