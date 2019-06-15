package com.fun_play.app.viewmodel.user;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseModel.BaseViewModel;
import com.fun_play.app.utils.ToastUtil;

public class LoginViewModel extends BaseViewModel {

    private Context mContext;

    public final ObservableField<String> username = new ObservableField<>();

    public final ObservableField<String> password = new ObservableField<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    private boolean verifyData() {
        if (TextUtils.isEmpty(username.get())) {
            ToastUtil.showToast(mContext.getResources().getString(R.string.edit_name));
            return false;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtil.showToast(mContext.getResources().getString(R.string.edit_password));
            return false;
        }
        return true;
    }

}
