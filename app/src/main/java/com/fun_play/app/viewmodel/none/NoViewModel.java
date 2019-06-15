package com.fun_play.app.viewmodel.none;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * @Description 没有viewModel的情况
 */

public class NoViewModel extends AndroidViewModel {

    public NoViewModel(@NonNull Application application) {
        super(application);
    }
}
