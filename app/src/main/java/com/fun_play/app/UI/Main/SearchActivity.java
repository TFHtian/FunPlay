package com.fun_play.app.UI.Main;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.fun_play.app.ModelCallback.Study.SearchResultCallback;
import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.GankAndroidAdapter;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivitySearchBinding;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;
import com.fun_play.app.utils.BaseTools;
import com.fun_play.app.view.CustomFooter;
import com.fun_play.app.viewmodel.homeMain.SearchViewModel;
import com.github.ybq.android.spinkit.style.Circle;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class SearchActivity extends BaseActivity<SearchViewModel,ActivitySearchBinding> implements SearchResultCallback{

    private String keyWord;
    private boolean isLoadData = false;
    private Circle mCircleDrawable;
    private GankAndroidAdapter gankAndroidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        isHideToolBar(true);
        showContentView();
        initToolBar();
        viewModel.setCallback(this);
        bindingView.setViewModel(viewModel);
        initView();
        initAnimation();
        initListener();
    }

    public void initToolBar(){
        setSupportActionBar(bindingView.toolbar);
        bindingView.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取热门搜索
        viewModel.getHotKey();
        viewModel.history.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                if (strings != null && strings.size() > 0) {
                    bindingView.clHistoryTag.setVisibility(View.VISIBLE);
                    showTagView(bindingView.tflSearchHistory, strings);
                } else {
                    bindingView.clHistoryTag.setVisibility(View.GONE);
                }
            }
        });

    }

    public void initView(){
        bindingView.header.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        bindingView.refreshLayout.setRefreshFooter(new CustomFooter(this));
        gankAndroidAdapter = new GankAndroidAdapter(this);
        bindingView.recyclerResult.setLayoutManager(new LinearLayoutManager(this));
        bindingView.recyclerResult.setAdapter(gankAndroidAdapter);
    }

    private void initAnimation() {
        //整体动画，获取到banner就停止
        mCircleDrawable = new Circle();
        mCircleDrawable.setColor(getResources().getColor(R.color.colorTheme));
        bindingView.ivLoading.setImageDrawable(mCircleDrawable);
    }

    public void initListener(){

        bindingView.etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = bindingView.etContent.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    bindingView.ivClear.setVisibility(View.VISIBLE);
                    keyWord = content;
                    loadSearch();
                } else {
                    bindingView.ivClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bindingView.ivHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clearHistory();
            }
        });

        bindingView.etContent.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            BaseTools.hideSoftKeyBoard(this);
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.saveSearch(keyWord);
            }
            return false;
        });

        bindingView.ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyWord = "";
                bindingView.etContent.setText("");
                bindingView.etContent.setFocusable(true);
                bindingView.etContent.setFocusableInTouchMode(true);
                bindingView.etContent.requestFocus();
                showLayoutView(true);
                BaseTools.showSoftKeyBoard(SearchActivity.this, bindingView.etContent);
            }
        });

        //刷新加载
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadData = true;
                viewModel.refreshResult(keyWord);
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadData = false;
                viewModel.loadResult(keyWord);
            }
        });
    }

    private void showLayoutView(boolean isShow) {
        if (isShow) {
            bindingView.nsSearchLayout.setVisibility(View.VISIBLE);
            bindingView.refreshLayout.setVisibility(View.GONE);
        } else {
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            bindingView.nsSearchLayout.setVisibility(View.GONE);
        }
    }

    public void loadSearch(){
        isLoadData = true;
        viewModel.refreshResult(keyWord);
        showLayoutView(false);
        showAnimLoading(true);
    }

    //热门搜索
    @Override
    public void HotKey(SearchTagBean searchTagBean) {
        if (searchTagBean != null && searchTagBean.getData().size() > 0) {
            bindingView.clSearchTag.setVisibility(View.VISIBLE);
            showTagView(bindingView.tflSearch, searchTagBean.getData());
        } else {
            bindingView.clSearchTag.setVisibility(View.GONE);
        }
        viewModel.getHistoryData();
    }

    @Override
    public void SearchResult(GankIoDataBean gankIoDataBean) {
        showAnimLoading(false);
        if (isLoadData) {
            if (gankIoDataBean.getResults()!=null){
                gankAndroidAdapter.setDataList(gankIoDataBean.getResults());
                bindingView.refreshLayout.finishRefresh();
                bindingView.refreshLayout.setNoMoreData(false);
            }
        } else {
            if (gankIoDataBean.getResults()!=null){
                gankAndroidAdapter.setNotifyDataChanged(gankIoDataBean.getResults());
                bindingView.refreshLayout.finishLoadMore();
            }else {
                bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    //显示热门搜索或历史搜索标签
    private <T> void showTagView(android.support.design.internal.FlowLayout flowLayout, final List<T> beanList) {
        flowLayout.removeAllViews();
        for (int i = 0; i < beanList.size(); i++) {
            TextView textView = (TextView) View.inflate(flowLayout.getContext(), R.layout.layout_system_tag, null);
            T bean = beanList.get(i);
            if (bean instanceof String) {
                textView.setText(Html.fromHtml((String) bean));
            } else if (bean instanceof SearchTagBean.DataBean) {
                SearchTagBean.DataBean dataBean = (SearchTagBean.DataBean) bean;
                textView.setText(Html.fromHtml(dataBean.getName()));
            }
            flowLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = "";
                    if (bean instanceof String) {
                        name = (String) bean;
                    } else if (bean instanceof SearchTagBean.DataBean) {
                        SearchTagBean.DataBean dataBean = (SearchTagBean.DataBean) bean;
                        name = dataBean.getName();
                    }
                    viewModel.keyWord.set(name);
                    viewModel.saveSearch(name);
                    BaseTools.hideSoftKeyBoard(SearchActivity.this);
                }
            });
        }
    }

    //是否显示动画
    private void showAnimLoading(Boolean isLoading) {
        if (isLoading != null && isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.refreshLayout.setVisibility(View.GONE);
            mCircleDrawable.start();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.refreshLayout.setVisibility(View.VISIBLE);
            mCircleDrawable.stop();
        }
    }

}
