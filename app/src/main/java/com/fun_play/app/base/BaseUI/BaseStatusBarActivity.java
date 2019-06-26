package com.fun_play.app.base.BaseUI;

import android.app.Activity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fun_play.app.R;
import com.fun_play.app.databinding.ActivityBaseStatusBinding;
import com.fun_play.app.utils.ClassUtil;
import com.github.ybq.android.spinkit.style.Circle;

public abstract class BaseStatusBarActivity <VM extends AndroidViewModel, SV extends ViewDataBinding> extends AppCompatActivity {

    // ViewModel
    protected VM viewModel;
    // 布局view
    protected SV bindingView;

    private View errorView;
    private View loadingView;
    private Circle mCircleDrawable;
    private ActivityBaseStatusBinding mBaseBarBinding;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mBaseBarBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base_status, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = mBaseBarBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBarBinding.getRoot());
        initAnimation();
        setToolBar();
        bindingView.getRoot().setVisibility(View.GONE);
        initViewModel();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtil.getViewModel(this);
        if (viewModelClass != null) {
            this.viewModel = ViewModelProviders.of(this).get(viewModelClass);
        }
    }

    private void initAnimation() {
        //整体动画，获取到banner就停止
        mCircleDrawable = new Circle();
        mCircleDrawable.setColor(getResources().getColor(R.color.colorTheme));
        loadingView = ((ViewStub) findViewById(R.id.vs_loading)).inflate();
        ImageView img = loadingView.findViewById(R.id.iv_loading);
        img.setImageDrawable(mCircleDrawable);
        mCircleDrawable.start();
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mBaseBarBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mBaseBarBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick(v);
            }
        });
    }

    public void isHideToolBar(Boolean isHide){
        if (isHide){
            mBaseBarBinding.toolBar.setVisibility(View.GONE);
        }else {
            mBaseBarBinding.toolBar.setVisibility(View.VISIBLE);
        }
    }

    public void onBackClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            onBackPressed();
        }
    }

    public int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    //设置沉浸式状态栏
    public void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBar = mBaseBarBinding.SysStatusBar;
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = getStatusHeight(activity);
            statusBar.setLayoutParams(layoutParams);
            setStatusTextBlack(true);
        }
    }

    //由子布局的statusBar动态设置状态栏高度
    public void setStatusBar(Activity activity, View barView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams layoutParams = barView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = getStatusHeight(activity);
            barView.setLayoutParams(layoutParams);
        }
    }

    //6.0后设置状态栏字体颜色
    public void setStatusTextBlack(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }

    protected void showContentView() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mCircleDrawable.isRunning()) {
            mCircleDrawable.stop();
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

}
