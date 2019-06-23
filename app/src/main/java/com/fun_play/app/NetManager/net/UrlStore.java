package com.fun_play.app.NetManager.net;

public class UrlStore {

    //  http://47.100.222.139:8090/api/v1
    public static final String BASE_HOST="http://47.111.165.229:8090/";

    public static final String APP_FACED_API="api/v1/";

    public static final String BASEURL_APP = BASE_HOST+APP_FACED_API;

    //刷新token接口
    public static final String Refresh_Token = "user/auth/refreshToken";

    //玩安卓
    public final static String API_WAN_ANDROID = "https://www.wanandroid.com/";

    //玩安卓轮播图接口
    public final static String WAN_ANDROID_BANNER = "banner/json";

    //干活
    public final static String API_GANKIO = "https://gank.io/api/";

    //获取干货数据接口
    public final static String Gtt_GankIo_Data = "data/{type}/{pre_page}/{page}";

    //新闻接
    public static final String News_Article_Cmpp_Api = "http://api.3g.ifeng.com/";

    //获取新闻列表接口
    public static final String Get_News_List_Data = "ClientNews";

}

