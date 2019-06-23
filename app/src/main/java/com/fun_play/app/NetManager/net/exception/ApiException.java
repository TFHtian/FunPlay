package com.fun_play.app.NetManager.net.exception;

import com.fun_play.app.NetManager.net.ErrorEnum;
import com.fun_play.app.NetManager.net.exception.base.BaseException;

/*
网络异常
 */
public class ApiException extends BaseException {

    private int httpCode;

    private ErrorEnum errorEnum=ErrorEnum.OTHER_EXCEPTION;;

    public ApiException(Throwable throwable, int httpCode) {
        super(throwable);
        this.httpCode=httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
}
