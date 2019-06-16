package com.fun_play.app.UI.Listen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fun_play.app.R;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentListenBinding;
import com.fun_play.app.viewmodel.None.NoViewModel;

public class ListenFragment extends BaseFragment<NoViewModel, FragmentListenBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_listen;
    }

}
