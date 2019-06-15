package com.fun_play.app.UI.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityLoginBinding;
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
}
