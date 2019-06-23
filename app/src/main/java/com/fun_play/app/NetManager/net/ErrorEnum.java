package com.fun_play.app.NetManager.net;

import android.content.Context;

public enum ErrorEnum {

    //common
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION","未知错误"),
    OTHER_EXCEPTION("NETWORK_EXCEPTION", "网络异常"),
    UNAUTH_EXCEPTION("UNAUTH_EXCEPTION", "token异常"),
    SUCCESS("SUCCESS", "操作成功"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "服务器内部异常"),
    DATABASE_ERROR("DATABASE_ERROR", ""),
    PARAM_ERROR("PARAM_ERROR", "参数错误"),
    TOKEN_EXPIRE("TOKEN_EXPIRE","token过期"),
    TOKEN_INVALID("TOKEN_INVALID","token无效");

    private String code;

    private String defaultMessage = "";

    private int msgResId = 0;


    ErrorEnum(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    ErrorEnum(String code, String defaultMessage, int msgResId) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.msgResId = msgResId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getErrorMessage(Context context) {
        String message = defaultMessage;
        if (msgResId!=0
                &&context.getResources().getString(this.msgResId) != null
                && !context.getString(this.msgResId).equals("")) {
            message = context.getString(this.msgResId);
        }
        return message;
    }
}
