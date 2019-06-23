package com.fun_play.app.NetManager.net.Authenicator;

import android.support.annotation.Nullable;

import com.fun_play.app.NetManager.net.ErrorEnum;
import com.fun_play.app.NetManager.net.ServiceGenerator;
import com.fun_play.app.NetManager.net.entity.ResponseError;
import com.fun_play.app.base.BaseApp.BaseApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String errorStr = response.body().string();
        ResponseError error = new Gson().fromJson(errorStr, ResponseError.class);
        ErrorEnum errorEnum=ErrorEnum.UNAUTH_EXCEPTION;
        try {
            errorEnum=ErrorEnum.valueOf(error.getError_code());
        }catch (Exception enumE){

        }
        switch (errorEnum){
            case TOKEN_EXPIRE:
                AuthResponse authResponse= ServiceGenerator.createServiceFrom(AuthenticateService.class).call_refreshToken().execute().body();
                if(authResponse!=null){
                    BaseApplication.setToken(authResponse.getToken());
                    return response.request().newBuilder()
                            .header("Authorization",authResponse.getToken())
                            .build();
                }
                break;
            case TOKEN_INVALID:

                break;
        }
        return null;
    }
}
