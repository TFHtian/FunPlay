package com.fun_play.app.UI.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.fun_play.app.UI.Main.HomeMainActivity;
import com.fun_play.app.R;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityLoginBinding;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.viewmodel.user.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setCenterTitle("登陆");
        showContentView();
        bindingView.setViewModel(viewModel);
    }

    public void login(View view){
       UIManager.finishAnimVertical(LoginActivity.this);
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onBackClick(View view) {
        super.onBackClick(view);
        UIManager.finishAnimVertical(LoginActivity.this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UIManager.finishAnimVertical(LoginActivity.this);
        return super.onKeyDown(keyCode, event);
    }
}
