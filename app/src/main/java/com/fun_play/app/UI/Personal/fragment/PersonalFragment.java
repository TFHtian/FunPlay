package com.fun_play.app.UI.Personal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentPersonalBinding;
import com.fun_play.app.viewmodel.None.NoViewModel;

public class PersonalFragment extends BaseFragment<NoViewModel, FragmentPersonalBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_personal;
    }
}
