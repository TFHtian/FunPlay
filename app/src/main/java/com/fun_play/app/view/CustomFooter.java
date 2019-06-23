package com.fun_play.app.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fun_play.app.R;
import com.github.ybq.android.spinkit.style.Circle;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class CustomFooter extends LinearLayout implements RefreshFooter {

    private TextView tvCustom;
    private ImageView imCustom;
    private Circle mCircleDrawable;

    public CustomFooter(Context context) {
        this(context,null,0);
    }

    public CustomFooter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.custom_footer_layout,this);
        tvCustom = view.findViewById(R.id.tv_custom);
        imCustom = view.findViewById(R.id.im_custom_anim);
        mCircleDrawable = new Circle();
        mCircleDrawable.setColor(getResources().getColor(R.color.colorTheme));
        imCustom.setImageDrawable(mCircleDrawable);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullUpToLoad:
                tvCustom.setText("上拉加载更多");
                imCustom.setVisibility(VISIBLE);
                mCircleDrawable.start();
                break;
            case Loading:
                tvCustom.setText("加载中...");
                imCustom.setVisibility(VISIBLE);
                mCircleDrawable.start();
                break;
            case ReleaseToLoad:
                tvCustom.setText("加载中...");
                imCustom.setVisibility(VISIBLE);
                mCircleDrawable.start();
                break;
        }
    }


    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        imCustom.setVisibility(GONE);
        if (success){
            tvCustom.setText("加载完成");
        } else {
            tvCustom.setText("加载失败");
        }
        if (mCircleDrawable!=null){
            mCircleDrawable.stop();
        }
        return 500;//延迟500毫秒之后再弹回
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }
}
