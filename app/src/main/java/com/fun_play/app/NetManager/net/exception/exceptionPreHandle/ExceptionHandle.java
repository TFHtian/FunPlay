package com.fun_play.app.NetManager.net.exception.exceptionPreHandle;

import com.fun_play.app.NetManager.net.ErrorEnum;
import com.fun_play.app.NetManager.net.entity.ResponseError;
import com.fun_play.app.NetManager.net.exception.ApiException;
import com.fun_play.app.NetManager.net.exception.OtherException;
import com.fun_play.app.NetManager.net.exception.UnauthException;
import com.fun_play.app.NetManager.net.exception.base.BaseException;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.HttpException;

public class ExceptionHandle {

    public static BaseException handleException(Throwable e) {

        BaseException baseException = null;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;

            if (httpException.code() == 400) {
                try {
                    String errorStr = httpException.response().errorBody().string();
                    ResponseError error = new Gson().fromJson(errorStr, ResponseError.class);
                    ErrorEnum errorEnum=ErrorEnum.UNKNOWN_EXCEPTION;
                    try {
                        errorEnum=ErrorEnum.valueOf(error.getError_code());
                        if(errorEnum.getCode().equals(error.getError_code())){
                            errorEnum.setDefaultMessage(error.getError_message());
                        }
                    }catch (Exception enumE){

                    }
                    baseException = new ApiException(e, httpException.code());
                    ((ApiException) baseException).setErrorEnum(errorEnum);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (httpException.code() == 401) {
                baseException = new UnauthException(e, httpException.code());
            } else {
                baseException = new ApiException(e, httpException.code());
            }
        }else if(e instanceof ApiException){
            baseException = (ApiException)e;
        } else if(e instanceof UnauthException){
            baseException = (UnauthException)e;
        }
        else {
            baseException = new OtherException(e);
        }

        return baseException;

    }
}
