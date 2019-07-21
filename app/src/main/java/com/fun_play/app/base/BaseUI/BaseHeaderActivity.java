package com.fun_play.app.base.BaseUI;

//联动布局

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fun_play.app.R;
import com.fun_play.app.databinding.BaseHeaderTitleBarBinding;
import com.fun_play.app.utils.CommonUtils;
import com.fun_play.app.utils.PerfectClickListener;
import com.fun_play.app.view.MyNestedScrollView;
import com.fun_play.app.view.statusbar.StatusBarUtil;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jp.wasabeef.glide.transformations.BlurTransformation;

public abstract class BaseHeaderActivity <HV extends ViewDataBinding, SV extends ViewDataBinding> extends AppCompatActivity {

    // 标题
    protected BaseHeaderTitleBarBinding bindingTitleView;
    // 内容布局头部
    protected HV bindingHeaderView;
    // 内容布局view
    protected SV bindingContentView;
    private View loadingView;
    private View refreshView;
    // 滑动多少距离后标题不透明
    private int slidingDistance;
    // 这个是高斯图背景的高度
    private int imageBgHeight;

    private AnimationDrawable mAnimationDrawable;
    private CompositeDisposable mCompositeDisposable;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View ll = getLayoutInflater().inflate(R.layout.activity_header_base, null);

        // 内容
        bindingContentView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        // 头部
        bindingHeaderView = DataBindingUtil.inflate(getLayoutInflater(), setHeaderLayout(), null, false);
        // 标题
        bindingTitleView = DataBindingUtil.inflate(getLayoutInflater(), R.layout.base_header_title_bar, null, false);

        // title (如自定义很强可以拿出去)
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bindingTitleView.getRoot().setLayoutParams(titleParams);
        RelativeLayout mTitleContainer = ll.findViewById(R.id.title_container);
        mTitleContainer.addView(bindingTitleView.getRoot());
        getWindow().setContentView(ll);

        // header
        RelativeLayout.LayoutParams headerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bindingHeaderView.getRoot().setLayoutParams(headerParams);
        RelativeLayout mHeaderContainer = ll.findViewById(R.id.header_container);
        mHeaderContainer.addView(bindingHeaderView.getRoot());
        getWindow().setContentView(ll);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingContentView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = ll.findViewById(R.id.container);
        mContainer.addView(bindingContentView.getRoot());
        getWindow().setContentView(ll);

        loadingView = ((ViewStub) getView(R.id.vs_loading)).inflate();
        refreshView = ((ViewStub) getView(R.id.vs_error_refresh)).inflate();
        refreshView.setVisibility(View.GONE);

        // 设置toolbar
        setToolBar();

        ImageView img = loadingView.findViewById(R.id.img_progress);
        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        // 点击加载失败布局
        refreshView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        bindingContentView.getRoot().setVisibility(View.GONE);

    }

    // a. 布局高斯透明图 header布局
    protected abstract int setHeaderLayout();

    // b. 设置头部header高斯背景 imgUrl
    protected abstract String setHeaderImgUrl();

    // c. 设置头部header布局 高斯背景ImageView控件
    protected abstract ImageView setHeaderImageView();

    //设置头部header布局 左侧的图片(需要设置曲线路径切换动画时重写)
    protected ImageView setHeaderPicView() {
        return new ImageView(this);
    }

    //1. 标题
    @Override
    public void setTitle(CharSequence text) {
        bindingTitleView.tbBaseTitle.setTitle(text);
    }

    // 2. 副标题
    protected void setSubTitle(CharSequence text) {
        bindingTitleView.tbBaseTitle.setSubtitle(text);
    }

    // 3. toolbar 单击"更多信息"
    protected void setTitleClickMore() {

    }

    //设置toolbar
    protected void setToolBar() {
        setSupportActionBar(bindingTitleView.tbBaseTitle);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }

        bindingTitleView.tbBaseTitle.setNavigationOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        });
    }

    /**
     * *** 初始化滑动渐变 一定要实现 ******
     *
     * @param imgUrl    header头部的高斯背景imageUrl
     * @param mHeaderBg header头部高斯背景ImageView控件
     */
    protected void initSlideShapeTheme(String imgUrl, ImageView mHeaderBg) {
        setImgHeaderBg(imgUrl);

        // toolbar 的高
        int toolbarHeight = bindingTitleView.tbBaseTitle.getLayoutParams().height;
        final int headerBgHeight = toolbarHeight + StatusBarUtil.getStatusBarHeight(this);

        // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
        ViewGroup.LayoutParams params = bindingTitleView.ivBaseTitleBarBg.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) bindingTitleView.ivBaseTitleBarBg.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);

        bindingTitleView.ivBaseTitleBarBg.setImageAlpha(0);
        //StatusBarUtils.setTranslucentImageHeader(this, 0, bindingTitleView.tbBaseTitle);
        com.jaeger.library.StatusBarUtil.setTranslucentForImageView(this,0,bindingTitleView.tbBaseTitle);

        // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
        if (mHeaderBg != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mHeaderBg.getLayoutParams();
            layoutParams.setMargins(0, -StatusBarUtil.getStatusBarHeight(this), 0, 0);

            ViewGroup.LayoutParams imgItemBgparams = mHeaderBg.getLayoutParams();
            // 获得高斯图背景的高度
            imageBgHeight = imgItemBgparams.height;
        }

        // 变色
        initScrollViewListener();
        initNewSlidingParams();
    }


    /**
     * 加载titlebar背景
     */
    private void setImgHeaderBg(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {

            // 高斯模糊背景 原来 参数：12,5  23,4
            Glide.with(this).load(imgUrl)
                    .error(R.drawable.stackblur_default)
                    .transform(new BlurTransformation(40, 8))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            bindingTitleView.tbBaseTitle.setBackgroundColor(Color.TRANSPARENT);
                            bindingTitleView.ivBaseTitleBarBg.setImageAlpha(0);
                            bindingTitleView.ivBaseTitleBarBg.setVisibility(View.VISIBLE);
                            return false;
                        }
                    }).into(bindingTitleView.ivBaseTitleBarBg);
        }
    }


    private void initScrollViewListener() {
        // 为了兼容23以下
        ((MyNestedScrollView) findViewById(R.id.mns_base)).setOnScrollChangeListener(new MyNestedScrollView.ScrollInterface() {
            @Override
            public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });
    }

    private void initNewSlidingParams() {
        int titleBarAndStatusHeight = (int) (CommonUtils.getDimens(R.dimen.nav_bar_height) + StatusBarUtil.getStatusBarHeight(this));
        // 减掉后，没到顶部就不透明了
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (CommonUtils.getDimens(R.dimen.base_header_activity_slide_more));
    }

    /**
     * 根据页面滑动距离改变Header方法
     */
    private void scrollChangeHeader(int scrolledY) {
        if (scrolledY < 0) {
            scrolledY = 0;
        }
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);

        Drawable drawable = bindingTitleView.ivBaseTitleBarBg.getDrawable();

        if (drawable == null) {
            return;
        }
        if (scrolledY <= slidingDistance) {
            // title部分的渐变
            drawable.mutate().setAlpha((int) (alpha * 255));
            bindingTitleView.ivBaseTitleBarBg.setImageDrawable(drawable);
        } else {
            drawable.mutate().setAlpha(255);
            bindingTitleView.ivBaseTitleBarBg.setImageDrawable(drawable);
        }
    }

    protected void showLoading() {
        if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
            loadingView.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (bindingContentView.getRoot().getVisibility() != View.GONE) {
            bindingContentView.getRoot().setVisibility(View.GONE);
        }
        if (refreshView != null && refreshView.getVisibility() != View.GONE) {
            refreshView.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refreshView != null && refreshView.getVisibility() != View.GONE) {
            refreshView.setVisibility(View.GONE);
        }
        if (bindingContentView.getRoot().getVisibility() != View.VISIBLE) {
            bindingContentView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refreshView != null && refreshView.getVisibility() != View.VISIBLE) {
            refreshView.setVisibility(View.VISIBLE);
        }
        if (bindingContentView.getRoot().getVisibility() != View.GONE) {
            bindingContentView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

    public void addSubscription(Disposable s) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(s);
    }

    @Override
    public void onDestroy() {
        if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable.clear();
        }
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
        super.onDestroy();
    }

}
