package com.fun_play.app.datamanager.bean.study;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

public class SystemListBean extends BaseObservable {

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    @Bindable
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Bindable
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Bindable
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean extends BaseObservable {

        private int curPage;
        private int offset;
        private int pageCount;
        private int size;
        private int total;
        private List<ArticlesBean> datas;

        @Bindable
        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        @Bindable
        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        @Bindable
        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        @Bindable
        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        @Bindable
        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        @Bindable
        public List<ArticlesBean> getDatas() {
            return datas;
        }

        public void setDatas(List<ArticlesBean> datas) {
            this.datas = datas;
        }
    }
}
