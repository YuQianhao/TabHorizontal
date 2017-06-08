package com.yuqianhao.stdlib;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/3/2.
 */

public class YWindow {


    private static DisplayMetrics gsDisplayMetrics=null;

    private YWindow(){}


    /**
     * 设置窗口的透明度  0-1
     * */
    public static void setWindowBackgroundAlpha(Activity activity,float fl){
        WindowManager.LayoutParams lp=activity.getWindow().getAttributes();
        lp.alpha=fl;
        activity.getWindow().setAttributes(lp);
    }
    /**
     * 获取屏幕相关的参数
     * */
    public static DisplayMetrics getWindowDisplayMetrics(Context activity){
        if(gsDisplayMetrics==null){
            gsDisplayMetrics=new DisplayMetrics();
            ((WindowManager)activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(gsDisplayMetrics);
        }
        return gsDisplayMetrics;
    }


}
