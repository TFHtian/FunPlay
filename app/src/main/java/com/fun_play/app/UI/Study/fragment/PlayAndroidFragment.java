package com.fun_play.app.UI.Study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentPlayAndroidBinding;
import com.fun_play.app.viewmodel.None.NoViewModel;

public class PlayAndroidFragment extends BaseFragment<NoViewModel, FragmentPlayAndroidBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_play_android;
    }
}
