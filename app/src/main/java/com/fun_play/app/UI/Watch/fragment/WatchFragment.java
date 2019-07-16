package com.fun_play.app.UI.Watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fun_play.app.R;
import com.fun_play.app.UI.Study.adapter.TabViewPageAdapter;
import com.fun_play.app.base.BaseUI.BaseFragment;
import com.fun_play.app.databinding.FragmentWatchBinding;
import com.fun_play.app.viewmodel.None.NoViewModel;

public class WatchFragment extends BaseFragment<NoViewModel, FragmentWatchBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_watch;
    }

    public void initView(){
        setViewPage();
        bindingView.viewpager.setOffscreenPageLimit(3);
        bindingView.tabWatch.setupWithViewPager(bindingView.viewpager);
        bindingView.tabWatch.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorTheme));
    }

    public void setViewPage(){
        TabViewPageAdapter adapter = new TabViewPageAdapter(getChildFragmentManager());
        adapter.addFragment(new WelfareFragment(), getResources().getString(R.string.welfare));
        adapter.addFragment(new FilmShowingFragment(),getResources().getString(R.string.film_showing));
        adapter.addFragment(new FilmComingFragment(),getResources().getString(R.string.film_coming));
        bindingView.viewpager.setAdapter(adapter);
    }


}
