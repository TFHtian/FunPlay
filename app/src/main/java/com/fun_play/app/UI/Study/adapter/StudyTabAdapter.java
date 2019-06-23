package com.fun_play.app.UI.Study.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fun_play.app.datamanager.bean.study.FilterBean;
import com.fun_play.app.datamanager.bean.study.NewFilterBean;

import java.util.ArrayList;
import java.util.List;

public class StudyTabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<NewFilterBean> mTitles = new ArrayList<>();

    public StudyTabAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, List<NewFilterBean> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getOption();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}

