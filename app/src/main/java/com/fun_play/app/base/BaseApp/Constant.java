package com.fun_play.app.base.BaseApp;

import com.fun_play.app.datamanager.bean.study.FilterBean;
import com.fun_play.app.datamanager.bean.study.NewFilterBean;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static final String All = "all";
    public static final String NewsCommonId = "SYLB10,SYDT10";

    //学习中的tab
    public static List<FilterBean> getStudyTab(){
        List<FilterBean> filterBeans = new ArrayList<>();
        filterBeans .add(new FilterBean("全部","all"));
        filterBeans .add(new FilterBean("IOS","iOS"));
        filterBeans .add(new FilterBean("前端","前端"));
        filterBeans.add(new FilterBean("App","App"));
        filterBeans.add(new FilterBean("拓展资源","拓展资源"));
        return filterBeans;
    }

    //新闻tab
    public static List<NewFilterBean> getNewsTab(){
        List<NewFilterBean> filterBeans = new ArrayList<>();
        filterBeans .add(new NewFilterBean("头条","SYLB10,SYDT10"));
        filterBeans .add(new NewFilterBean("娱乐","YL53,FOCUSYL53"));
        filterBeans .add(new NewFilterBean("军事","JS83,FOCUSJS83"));
        filterBeans .add(new NewFilterBean("体育","TY43,FOCUSTY43,TYLIVE,TYTOPIC"));
        filterBeans .add(new NewFilterBean("财经","CJ33,FOCUSCJ33,HNCJ33"));
        filterBeans .add(new NewFilterBean("科技","KJ123,FOCUSKJ123"));
        filterBeans .add(new NewFilterBean("历史","LS153,FOCUSLS153"));
        filterBeans .add(new NewFilterBean("汽车","QC45,FOCUSQC45"));
        filterBeans .add(new NewFilterBean("社会","SH133,FOCUSSH133"));
        filterBeans .add(new NewFilterBean("时尚","SS78,FOCUSSS78"));
        filterBeans .add(new NewFilterBean("国学","GXPD,FOCUSGXPD"));
        filterBeans .add(new NewFilterBean("文化","WH25,FOCUSWH25"));
        return filterBeans;
    }

}
