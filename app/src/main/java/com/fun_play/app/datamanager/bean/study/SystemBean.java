package com.fun_play.app.datamanager.bean.study;

import android.databinding.BaseObservable;

import java.util.List;

public class SystemBean extends BaseObservable {

    private int errorCode;
    private String errorMsg;
    private List<SystemItemBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<SystemItemBean> getData() {
        return data;
    }

    public void setData(List<SystemItemBean> data) {
        this.data = data;
    }
}
