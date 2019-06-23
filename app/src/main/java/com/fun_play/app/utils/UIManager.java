package com.fun_play.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fun_play.app.R;

/**
 * 跳转界面管理类
 */

public class UIManager {

    /**
     * 普通不传值跳转
     * @param context
     * @param cls
     */
    public static void switcherNormal(Context context, Class<?> cls) {
        switcherNormal(context,cls, null);
    }

    /**
     * 普通跳转带值
     * @param context
     * @param cls
     */
    public static void switcherNormal(Context context,Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    /**
     * 标记跳转
     * @param context
     * @param cls
     */
    public static void switcherNormal(Context context,Class<?> cls,int flags){
        Intent intent = new Intent();
        intent.setClass(context,cls);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    /**
     * 右进左出不传值跳转
     * @param context
     * @param cls
     */
    public static void switcherAnimHorizontal(Context context, Class<?> cls) {
        switcherAnimHorizontal(context,cls, null);
    }

    /**
     * 右进左出跳转带值
     * @param context
     * @param cls
     */
    public static void switcherAnimHorizontal(Context context,Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
    }

    /**
     * 下进上出不传值跳转
     * @param context
     * @param cls
     */
    public static void switcherAnimVertical(Context context, Class<?> cls) {
        switcherAnimVertical(context,cls, null);
    }

    /**
     * 下进上出跳转带值
     * @param context
     * @param cls
     */
    public static void switcherAnimVertical(Context context,Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.activity_bottom_in, R.anim.activity_bottom_silent);
    }

    /**
     * 横向退出动画
     * @param context
     */
    public static void finishAnimHorizontal(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.activity_left_out, R.anim.activity_right_in);
    }

    /**
     * 纵向退出动画
     * @param context
     */
    public static void finishAnimVertical(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.activity_bottom_silent, R.anim.activity_bottom_out);
    }

}
