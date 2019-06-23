package com.fun_play.app.UI.Study.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fun_play.app.R;

@SuppressLint("ValidFragment")
public class SimpleTabFragment extends Fragment {
    private String mTitle;

    public static SimpleTabFragment getInstance(String title) {
        SimpleTabFragment sf = new SimpleTabFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_simple_tab, null);
        return v;
    }
}