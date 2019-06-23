package com.fun_play.app.NetManager.net.Authenicator;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private String token;
    private int expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
