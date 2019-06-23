package com.fun_play.app.NetManager.net.Authenicator;

import com.fun_play.app.NetManager.net.UrlStore;

import retrofit2.Call;
import retrofit2.http.PUT;

public interface AuthenticateService {

    //刷新token
    @PUT(UrlStore.Refresh_Token)
    Call<AuthResponse> call_refreshToken();

}
