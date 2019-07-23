package com.fun_play.app.UI.Watch.activity;


import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fun_play.app.ModelMVP.Watch.contract.FilmDetailContract;
import com.fun_play.app.ModelMVP.Watch.presenter.FilmDetailPresenterImpl;
import com.fun_play.app.R;
import com.fun_play.app.UI.Watch.adapter.FilmDetailActorAdapter;
import com.fun_play.app.UI.Watch.adapter.FilmDetailStillAdapter;
import com.fun_play.app.base.BaseAdapter.OnItemClickListener;
import com.fun_play.app.base.BaseApp.Constant;
import com.fun_play.app.base.BaseUI.BaseHeaderActivity;
import com.fun_play.app.databinding.ActivityFilmDetailBinding;
import com.fun_play.app.databinding.HeaderFilmDetailBinding;
import com.fun_play.app.datamanager.bean.study.GankIoDataBean;
import com.fun_play.app.datamanager.bean.watch.FilmDetailBean;
import com.fun_play.app.datamanager.bean.watch.FilmDetailViewManager;
import com.fun_play.app.datamanager.bean.watch.FilmItemBean;
import com.fun_play.app.utils.DensityUtil;
import com.fun_play.app.utils.UIManager;
import com.fun_play.app.utils.decoration.SpacesItemDecoration;
import com.fun_play.app.view.PicturePreview.PicturePreviewActivity;
import com.fun_play.app.view.WebView.WebViewActivity;
import com.github.ybq.android.spinkit.style.WanderingCubes;

import java.util.ArrayList;
import java.util.List;

public class FilmDetailActivity extends BaseHeaderActivity<HeaderFilmDetailBinding, ActivityFilmDetailBinding> implements FilmDetailContract.View{

    private FilmItemBean filmItemBean;
    private WanderingCubes wanderingCubes;
    private FilmDetailViewManager filmDetailViewManager;
    public ObservableField<Boolean> isShowActor = new ObservableField<>();
    public ObservableField<Boolean> isShowBoxOffice = new ObservableField<>();
    public ObservableField<Boolean> isShowVideo = new ObservableField<>();
    private FilmDetailContract.Presenter filmDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        initView();
        initAnimation();
        init();
    }

    public void initView(){
        showContentView();
    }

    public void init(){
        filmDetailViewManager = new FilmDetailViewManager();
        filmDetailPresenter = new FilmDetailPresenterImpl(this);
        if (getIntent() != null) {
            filmItemBean = (FilmItemBean) getIntent().getSerializableExtra(Constant.FilmBean);
        }
        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView());
        setTitle(filmItemBean.getTCn());
        setSubTitle(filmItemBean.getTEn());
        bindingHeaderView.setSubjectsBean(filmItemBean);
        bindingHeaderView.executePendingBindings();

        bindingContentView.setViewManager(filmDetailViewManager);

        filmDetailPresenter.GetFilmDetail(filmItemBean.getId());
    }

    private void initAnimation() {
        wanderingCubes = new WanderingCubes();
        wanderingCubes.setColor(getResources().getColor(R.color.colorTheme));
        bindingContentView.ivLoading.setImageDrawable(wanderingCubes);
        wanderingCubes.start();
    }


    @Override
    protected int setHeaderLayout() {
        return R.layout.header_film_detail;
    }

    @Override
    protected String setHeaderImgUrl() {
        if (filmItemBean == null) {
            return "";
        }
        return filmItemBean.getImg();
    }

    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }

    @Override
    public void FilmDetail(FilmDetailBean filmDetailBean) {
        showAnimation(false);
        if (filmDetailBean != null && filmDetailBean.getData() != null) {
            if (filmDetailBean.getData().getBasic() != null) {
                FilmDetailBean.FilmDetailDataBean.BasicBean basic = filmDetailBean.getData().getBasic();
                bindingContentView.setBean(basic);
                transformData(filmDetailBean);
            }

            if (filmDetailBean.getData().getBoxOffice() != null
                    && !TextUtils.isEmpty(filmDetailBean.getData().getBoxOffice().getTodayBoxDes())
                    && !TextUtils.isEmpty(filmDetailBean.getData().getBoxOffice().getTotalBoxDes())) {
                isShowBoxOffice.set(true);
                filmDetailViewManager.isShowBoxOffice.set(true);
                bindingContentView.setBoxOffice(filmDetailBean.getData().getBoxOffice());
            } else {
                isShowBoxOffice.set(false);
                filmDetailViewManager.isShowBoxOffice.set(false);
            }

            if (filmDetailBean.getData().getBasic().getVideo() != null
                    && !TextUtils.isEmpty(filmDetailBean.getData().getBasic().getVideo().getUrl())) {
                FilmDetailBean.FilmDetailDataBean.BasicBean.VideoBean video = filmDetailBean.getData().getBasic().getVideo();
                bindingContentView.setVideo(video);
                isShowVideo.set(true);
                filmDetailViewManager.isShowVideo.set(true);
                DensityUtil.formatHeight(bindingContentView.ivVideo, DensityUtil.getDisplayWidth() - DensityUtil.dip2px(40), (640f / 360), 3);
                DensityUtil.setViewMargin(bindingContentView.ivVideo, true, 10, 10, 10, 10);
                bindingContentView.ivVideo.setOnClickListener(view -> WebViewActivity.loadUrl(this, video.getHightUrl(), video.getTitle(), true));
            }else {
                isShowVideo.set(false);
                filmDetailViewManager.isShowVideo.set(false);
            }
        }
    }

    public void showAnimation(boolean isShow){
        if (isShow){
            bindingContentView.llContent.setVisibility(View.GONE);
            bindingContentView.llLoading.setVisibility(View.VISIBLE);
            wanderingCubes.start();
        }else {
            bindingContentView.llContent.setVisibility(View.VISIBLE);
            bindingContentView.llLoading.setVisibility(View.GONE);
            wanderingCubes.stop();
        }
    }

    //相关列表处理
    private void transformData(final FilmDetailBean bean) {
        //演职员列表
        if (bean.getData().getBasic().getActors() != null && bean.getData().getBasic().getActors().size() > 0) {
            isShowActor.set(true);
            filmDetailViewManager.isShowActor.set(true);
            FilmDetailBean.ActorsBean director = bean.getData().getBasic().getDirector();
            if (director != null) {
                director.setRoleName(getResources().getString(R.string.film_director));
                bean.getData().getBasic().getActors().add(0, director);
            }
            setActorAdapter(bean.getData().getBasic().getActors());
        } else {
            isShowActor.set(false);
            filmDetailViewManager.isShowActor.set(false);
        }

        if (bean.getData().getBasic().getStageImg() != null
                && bean.getData().getBasic().getStageImg().getList() != null
                && bean.getData().getBasic().getStageImg().getList().size() > 0) {
            setStillsAdapter(bean.getData().getBasic().getStageImg().getList());
        }
    }

    //演职员适配
    public void setActorAdapter(List<FilmDetailBean.ActorsBean> listBeans){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(FilmDetailActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bindingContentView.xrvCast.setLayoutManager(mLayoutManager);
        // 需加，不然滑动不流畅
        bindingContentView.xrvCast.setNestedScrollingEnabled(false);
        bindingContentView.xrvCast.setHasFixedSize(false);
        bindingContentView.xrvCast.addItemDecoration(new SpacesItemDecoration(DensityUtil.dip2px(5),0));

        FilmDetailActorAdapter mAdapter = new FilmDetailActorAdapter();
        mAdapter.addAll(listBeans);
        bindingContentView.xrvCast.setAdapter(mAdapter);
        bindingContentView.xrvCast.setFocusable(false);
        bindingContentView.xrvCast.setFocusableInTouchMode(false);

        //图片预览
        mAdapter.setOnItemClickListener(new OnItemClickListener<FilmDetailBean.ActorsBean>() {
            @Override
            public void onClick(FilmDetailBean.ActorsBean actorsBean, int position) {
                List<String> pictureList = new ArrayList<>();
                for (FilmDetailBean.ActorsBean actorsBeans : listBeans){
                    pictureList.add(actorsBeans.getImg());
                }
                if (pictureList.size()>0){
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.Position,position);
                    bundle.putStringArrayList(Constant.PictureList, (ArrayList<String>) pictureList);
                    UIManager.switcherNormal(FilmDetailActivity.this, PicturePreviewActivity.class,bundle);
                }
            }
        });
    }

    //剧照适配
    private void setStillsAdapter(List<FilmDetailBean.ImageListBean> listBeans) {
        bindingContentView.xrvImages.setVisibility(View.VISIBLE);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(FilmDetailActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bindingContentView.xrvImages.setLayoutManager(mLayoutManager);
        // 需加，不然滑动不流畅
        bindingContentView.xrvImages.setNestedScrollingEnabled(false);
        bindingContentView.xrvImages.setHasFixedSize(false);
        bindingContentView.xrvCast.addItemDecoration(new SpacesItemDecoration(DensityUtil.dip2px(10),0));

        FilmDetailStillAdapter mAdapter = new FilmDetailStillAdapter();
        mAdapter.addAll(listBeans);
        bindingContentView.xrvImages.setAdapter(mAdapter);
        bindingContentView.xrvImages.setFocusable(false);
        bindingContentView.xrvImages.setFocusableInTouchMode(false);

        //图片预览
        mAdapter.setOnItemClickListener(new OnItemClickListener<FilmDetailBean.ImageListBean>() {
            @Override
            public void onClick(FilmDetailBean.ImageListBean imageListBean, int position) {
                List<String> pictureList = new ArrayList<>();
                for (FilmDetailBean.ImageListBean imageListBeans : listBeans){
                    pictureList.add(imageListBeans.getImgUrl());
                }
                if (pictureList.size()>0){
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.Position,position);
                    bundle.putStringArrayList(Constant.PictureList, (ArrayList<String>) pictureList);
                    UIManager.switcherNormal(FilmDetailActivity.this, PicturePreviewActivity.class,bundle);
                }
            }
        });
    }

}
