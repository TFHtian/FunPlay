package com.fun_play.app.viewmodel.homeMain;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fun_play.app.ModelCallback.Study.SearchResultCallback;
import com.fun_play.app.ModelMVP.Study.contract.SearchResultContract;
import com.fun_play.app.ModelMVP.Study.presenter.SearchResultPresenterImpl;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.study.SearchTagBean;
import com.fun_play.app.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends BaseViewModel implements SearchResultContract.View{

    public final ObservableField<String> keyWord = new ObservableField<>();
    private SearchResultContract.Presenter searchResultPresenter;
    private SearchResultCallback searchResultCallback;
    public final MutableLiveData<List<String>> history = new MutableLiveData<>();
    private List<String> searchHistory;
    private Gson gson;
    private int page = 1;
    private String type = Constant.All_Type;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        searchResultPresenter = new SearchResultPresenterImpl(this);
    }

    public void setCallback( SearchResultCallback searchResultCallback){
        this.searchResultCallback = searchResultCallback;
    }

    public void getHotKey(){
        searchResultPresenter.getHotKey();
    }

    public void refreshResult(String keyword){
        page = 1;
        searchResultPresenter.searchResult(page,type,keyword);
    }

    public void loadResult(String keyword){
        page ++;
        searchResultPresenter.searchResult(page,type,keyword);
    }

    @Override
    public void SearchResult(GankIoDataBean gankIoDataBean) {
        searchResultCallback.SearchResult(gankIoDataBean);
    }

    @Override
    public void HotKey(SearchTagBean searchTagBean) {
        searchResultCallback.HotKey(searchTagBean);
    }

    //保存成历史搜索
    public void saveSearch(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            if (searchHistory == null) {
                searchHistory = getSearchHistory();
            }
            if (searchHistory != null) {
                if (searchHistory.size() > 0) {
                    searchHistory.remove(keyword);
                }
                searchHistory.add(0, keyword);
                if (searchHistory.size() > 12) {
                    searchHistory.remove(searchHistory.size() - 1);
                }
            }
            if (gson == null) {
                gson = new Gson();
            }
            SPUtils.putString(Constant.SEARCH_HISTORY, gson.toJson(searchHistory));
            history.setValue(searchHistory);
        }
    }

    // 清空历史记录
    public void clearHistory() {
        SPUtils.putString(Constant.SEARCH_HISTORY, "");
        if (searchHistory != null) {
            searchHistory.clear();
        }
        history.setValue(null);
    }

    //获得历史记录
    public void getHistoryData() {
        history.setValue(getSearchHistory());
    }

    public List<String> getSearchHistory() {
        try {
            String details = SPUtils.getString(Constant.SEARCH_HISTORY, "");
            if (!TextUtils.isEmpty(details)) {
                if (gson == null) {
                    gson = new Gson();
                }
                return gson.fromJson(details, new TypeToken<List<String>>() {
                }.getType());
            } else {
                return new ArrayList<String>();
            }
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        gson = null;
        if (searchHistory != null) {
            searchHistory.clear();
            searchHistory = null;
        }
    }

}
