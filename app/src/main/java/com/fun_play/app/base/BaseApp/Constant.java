package com.fun_play.app.base.BaseApp;

import com.fun_play.app.datamanager.bean.study.FilterBean;
import com.fun_play.app.datamanager.bean.study.NewFilterBean;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static final String All = "全部";
    public static final String All_Type = "all";
    public static final String IOS = "IOS";
    public static final String IOS_Type = "iOS";
    public static final String App = "App";
    public static final String Web = "前端";
    public static final String Movie = "休息视频";
    public static final String Source = "拓展资源";
    public static final String GANK_CALA = "gank_cala";
    public static final String Android = "Android";
    public static final String Welfare = "福利";


    public static final String NewsCommonId = "SYLB10,SYDT10";
    public static final String AID = "aid";
    public static final String FilmBean = "FilmBean";
    public static final String mTitle = "mTitle";
    public static final String mUrl = "mUrl";
    public static final String isTitleFix = "isTitleFix";
    public static final String Net_Error = "当前网络不可用，请检查你的网络设置";
    public static final String PictureList = "pictureList";
    public static final String Position = "position";


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
        filterBeans .add(new NewFilterBean("时尚","SS78,FOCUSSS78"));
        filterBeans .add(new NewFilterBean("国学","GXPD,FOCUSGXPD"));
        filterBeans .add(new NewFilterBean("文化","WH25,FOCUSWH25"));
        return filterBeans;
    }

}
