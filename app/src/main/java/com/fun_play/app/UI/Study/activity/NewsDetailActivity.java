package com.fun_play.app.UI.Study.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fun_play.app.ModelCallback.Study.NewsDetailDataCallback;
import com.fun_play.app.R;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityNewsDetailBinding;
import com.fun_play.app.datamanager.bean.study.NewsArticleBean;
import com.fun_play.app.utils.GlideUtil;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.viewmodel.study.NewsDetailViewModel;
import com.jaeger.library.StatusBarUtil;

public class NewsDetailActivity extends BaseActivity<NewsDetailViewModel, ActivityNewsDetailBinding> implements NewsDetailDataCallback {

    private String aid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        isHideToolBar(true);
        initToolBar();
        bindingView.setViewModel(viewModel);
        viewModel.SetCallback(this);
        init();
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageView(this,0,bindingView.toolBar);
    }

    public void init(){
        aid = getIntent().getStringExtra(Constant.AID);
        viewModel.getNewsDetail(aid);
    }

    protected void initToolBar() {
        setSupportActionBar(bindingView.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        bindingView.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.finishAnimHorizontal(NewsDetailActivity.this);
            }
        });
    }

    @Override
    public void NewsDetail(NewsArticleBean newsArticleBean) {
        showContentView();
        //封面图
        if (newsArticleBean!=null&&newsArticleBean.getBody()!=null){
            GlideUtil.displayGaussian(NewsDetailActivity.this,newsArticleBean.getBody().getThumbnail(), bindingView.imCover);
            bindingView.setDetailBean(newsArticleBean.getBody());
            initWebView(newsArticleBean.getBody().getText());
        }
    }

    //初始化WebView，加载具体内容
    private void initWebView(String content){
        //设置webview
        bindingView.webView.setWebChromeClient(new MyWebChromeClient());
        bindingView.webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = bindingView.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //开启javascript
        webSettings.setDomStorageEnabled(true);  //开启DOM
        webSettings.setDefaultTextEncodingName("utf-8"); //设置编码
        // // web页面处理
        webSettings.setAllowFileAccess(true);// 支持文件流
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
//        webSettings.setBlockNetworkImage(true);
        //开启缓存机制
        webSettings.setAppCacheEnabled(true);
        /*
        在我们获取到的String类型的html代码里面，我们已经把转义符replace成我们实际需要的字符，
        这时候我们就能取到img的标签了(<img>)，那只要加上如下的代码，就可以了：
         */
        String htmlData = content.replace("<img", "<img style='max-width:100%;height:auto;'");
        bindingView.webView.loadDataWithBaseURL(content,htmlData,"text/html","utf-8","");
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.d("zttjiangqq","加载进度发生变化:"+newProgress);
        }
    }
    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("zttjiangqq","网页开始加载:"+url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("zttjiangqq","网页加载完成..."+url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.d("zttjiangqq","加载的资源:"+url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("zttjiangqq","拦截到URL信息为:"+url);
            return super.shouldOverrideUrlLoading(view, url);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UIManager.finishAnimHorizontal(NewsDetailActivity.this);
        return super.onKeyDown(keyCode, event);
    }
}
