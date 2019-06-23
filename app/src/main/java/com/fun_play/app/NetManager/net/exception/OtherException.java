package com.fun_play.app.NetManager.net.exception;

import com.fun_play.app.NetManager.net.ErrorEnum;
import com.fun_play.app.NetManager.net.exception.base.BaseException;

public class OtherException extends BaseException {

    private ErrorEnum errorEnum= ErrorEnum.UNKNOWN_EXCEPTION;

    public OtherException(Throwable throwable) {
        super(throwable);
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
}
