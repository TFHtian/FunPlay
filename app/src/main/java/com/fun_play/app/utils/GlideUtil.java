package com.fun_play.app.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.fun_play.app.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class GlideUtil {

    private static GlideUtil instance;

    private GlideUtil() {
    }

    public static GlideUtil getInstance() {
        if (instance == null) {
            instance = new GlideUtil();
        }
        return instance;
    }

    public static void displayEspImage(String url, ImageView imageView, int type) {
        Glide.with(imageView.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(getDefaultPic(type))
                .error(getDefaultPic(type))
                .into(imageView);
    }

    private static int getDefaultPic(int type) {
        switch (type) {
            case 0:
                return R.drawable.img_default_movie;
            case 1:
                return R.drawable.img_default_meizi;
            case 2:
                return R.drawable.img_default_book;
            case 3:
                return R.drawable.shape_bg_loading;
            default:
                break;
        }
        return R.drawable.img_default_meizi;
    }

    //将gif图转换为静态图

    public static void displayGif(String url, ImageView imageView) {

        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.shape_bg_loading)
                .error(R.drawable.shape_bg_loading)
                .into(imageView);
    }

    public static void displayNews(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .transform(new BlurTransformation(10, 1))
                .into(imageView);
    }

    private static void displayGaussian(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .transform(new BlurTransformation(50, 8))
                .into(imageView);
    }

    @BindingAdapter({"android:displayFadeImage", "android:defaultPicType"})
    public static void displayFadeImage(ImageView imageView, String url, int defaultPicType) {
        displayEspImage(url, imageView, defaultPicType);
    }

    @BindingAdapter("android:showMovieImg")
    public static void showMovieImg(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .override((int) CommonUtils.getDimens(R.dimen.movie_detail_width), (int) CommonUtils.getDimens(R.dimen.movie_detail_height))
                .placeholder(getDefaultPic(0))
                .error(getDefaultPic(0))
                .into(imageView);
    }

    @BindingAdapter("android:showImgBg")
    public static void showImgBg(ImageView imageView, String url) {
        displayGaussian(imageView.getContext(), url, imageView);
    }

    @BindingAdapter({"android:imageUrl", "android:imageWidthDp", "android:imageHeightDp"})
    public static void imageUrl(ImageView imageView, String url, int imageWidthDp, int imageHeightDp) {
        Glide.with(imageView.getContext())
                .load(url)
                .override(DensityUtil.dip2px(imageWidthDp), DensityUtil.dip2px(imageHeightDp))
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(getDefaultPic(3))
                .centerCrop()
                .error(getDefaultPic(0))
                .into(imageView);
    }

}
