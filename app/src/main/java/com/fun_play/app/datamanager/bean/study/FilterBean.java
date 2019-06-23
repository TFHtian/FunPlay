package com.fun_play.app.datamanager.bean.study;

public class FilterBean {
    private String option;
    private String result;

    public FilterBean(String option, String result) {
        this.option = option;
        this.result = result;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
