package com.fun_play.app.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.fun_play.app.R;

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
//                .skipMemoryCache(true) //跳过内存缓存
//                .crossFade(1000)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)// 缓存图片源文件（解决加载gif内存溢出问题）
//                .into(new GlideDrawableImageViewTarget(imageView, 1));
                .into(imageView);
    }

}
