package com.fun_play.app.UI.Main;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fun_play.app.R;
import com.fun_play.app.UI.Listen.fragment.ListenFragment;
import com.fun_play.app.UI.Personal.fragment.PersonalFragment;
import com.fun_play.app.UI.Study.fragment.StudyFragment;
import com.fun_play.app.UI.User.LoginActivity;
import com.fun_play.app.UI.Watch.fragment.WatchFragment;
import com.fun_play.app.base.BaseUI.BaseActivity;
import com.fun_play.app.databinding.ActivityHomeMainBinding;
import com.fun_play.app.databinding.NavHeaderHomeMainBinding;
import com.fun_play.app.utils.CommonUtils;
import com.fun_play.app.utils.PerfectClickListener;
import com.fun_play.app.utils.ToastUtil;
import com.fun_play.app.view.MyFragmentPagerAdapter;
import com.fun_play.app.view.statusbar.StatusBarUtil;
import com.fun_play.app.viewmodel.homeMain.HomeMainViewModel;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomeMainActivity extends BaseActivity<HomeMainViewModel, ActivityHomeMainBinding> implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private FrameLayout llTitleMenu;
    private Toolbar toolbar;
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ViewPager vpContent;
    private TextView tvTitleStudy,tvTitleWatch,tvTitleListen,tvTitlePersonal;
    private NavHeaderHomeMainBinding bind;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        showContentView();
        isHideToolBar(true);
        bindingView.setViewModel(viewModel);
        initStatusView();
        initId();

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(HomeMainActivity.this, drawerLayout,
                CommonUtils.getColor(R.color.colorTheme));
        initContentFragment();
        initDrawerLayout();
        initListener();
    }

    //状态栏
    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = bindingView.include.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        bindingView.include.viewStatus.setLayoutParams(layoutParams);
    }

    //view
    private void initId() {
        drawerLayout = bindingView.drawerLayout;
        navView = bindingView.navView;
        toolbar = bindingView.include.toolbar;
        llTitleMenu = bindingView.include.llTitleMenu;
        vpContent = bindingView.include.vpContent;
        tvTitleStudy = bindingView.include.tvTitleStudy;
        tvTitleWatch = bindingView.include.tvTitleWatch;
        tvTitleListen = bindingView.include.tvTitleListen;
        tvTitlePersonal = bindingView.include.tvTitlePersonal;
    }

    //top tab监听
    private void initListener() {
        llTitleMenu.setOnClickListener(this);
        tvTitleStudy.setOnClickListener(this);
        tvTitleWatch.setOnClickListener(this);
        tvTitleListen.setOnClickListener(this);
        tvTitlePersonal.setOnClickListener(this);
    }

    //初始化抽屉
    private void initDrawerLayout() {
        navView.inflateHeaderView(R.layout.nav_header_home_main);
        View headerView = navView.getHeaderView(0);
        bind = DataBindingUtil.bind(headerView);
        bind.setViewModel(viewModel);

        bind.llNavAbout.setOnClickListener(this);
        bind.llNavLogin.setOnClickListener(listener);
        bind.llNavExit.setOnClickListener(this);
        bind.ivAvatar.setOnClickListener(this);
    }

    //tab板块
    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new StudyFragment());
        mFragmentList.add(new WatchFragment());
        mFragmentList.add(new ListenFragment());
        mFragmentList.add(new PersonalFragment());
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        setCurrentItem(0);
    }

    //抽屉item监听
    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(final View v) {
            bindingView.drawerLayout.closeDrawer(GravityCompat.START);
            bindingView.drawerLayout.postDelayed(() -> {
                switch (v.getId()) {
                    case R.id.ll_nav_about:// 关于

                        break;
                    case R.id.ll_nav_login://登陆
                        LoginActivity.start(HomeMainActivity.this);
                        overridePendingTransition(R.anim.activity_bottom_in, R.anim.activity_bottom_silent);
                        break;
                    case R.id.ll_nav_exit://退出

                        break;
                    default:
                        break;
                }
            }, 260);
        }
    };

    //tab 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:
                // 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.tv_title_study:
                // 不然cpu会有损耗
                if (vpContent.getCurrentItem() != 0) {
                    setCurrentItem(0);
                }
                break;
            case R.id.tv_title_watch:
                if (vpContent.getCurrentItem() != 1) {
                    setCurrentItem(1);
                }
                break;
            case R.id.tv_title_listen:
                if (vpContent.getCurrentItem() != 2) {
                    setCurrentItem(2);
                }
                break;
            case R.id.tv_title_personal:
                if (vpContent.getCurrentItem() != 3) {
                    setCurrentItem(3);
                }
                break;
            default:
                break;
        }
    }

    //tab 切换界面
    private void setCurrentItem(int position) {
        boolean isStudy = false,isWatch = false,isListen = false,isPersonal = false;
        Typeface studyType = Typeface.defaultFromStyle(Typeface.NORMAL);
        Typeface watchType = Typeface.defaultFromStyle(Typeface.NORMAL);
        Typeface listenType = Typeface.defaultFromStyle(Typeface.NORMAL);
        Typeface personalType = Typeface.defaultFromStyle(Typeface.NORMAL);
        switch (position) {
            case 0:
                isStudy = true;
                studyType = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
            case 1:
                isWatch = true;
                watchType = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
            case 2:
                isListen = true;
                listenType = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
            case 3:
                isPersonal = true;
                personalType = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
            default:
                isStudy = true;
                studyType = Typeface.defaultFromStyle(Typeface.BOLD);
                break;
        }
        vpContent.setCurrentItem(position);
        tvTitleStudy.setSelected(isStudy);
        tvTitleStudy.setTypeface(studyType);
        tvTitleWatch.setSelected(isWatch);
        tvTitleWatch.setTypeface(watchType);
        tvTitleListen.setSelected(isListen);
        tvTitleListen.setTypeface(listenType);
        tvTitlePersonal.setSelected(isPersonal);
        tvTitlePersonal.setTypeface(personalType);
    }

    //viewPage滑动
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setCurrentItem(0);
                break;
            case 1:
                setCurrentItem(1);
                break;
            case 2:
                setCurrentItem(2);
                break;
            case 3:
                setCurrentItem(3);
                break;
            default:
                break;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_add:
//                ToastUtil.showToast("不给你看");
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bindingView.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                bindingView.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void addSubscription(Disposable s) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable.clear();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
