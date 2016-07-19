package com.zkr.jsw.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by lenovo on 2016/3/17.
 */
public class Constants {
    /**
     * sharepre共享文件名
     */
    public static final String SHARE_FILES = "JSW_FILES";
    /**
     * sd卡下载的文件夹名称
     */
    public static final  String SD_FILE_NAME = "zkrjsw";
    /**
     * log输出标识
     */
    public static final String TAG = "gnifeifeiing";
    /**
     * 默认每页显示的item数量
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 网络请求失败监听
     */
    public static final String VOLLEY_ERROR = "请求失败";
    /**
     * 网络请求时对话框显示文字
     */
    public static final String IN_THE_REQUEST_TEXT = "请求中...";
    /**
     * 服务器地址
     * 北京：http://114.110.8.114
     * 郑州：http://192.168.1.252:9401
     */
//    public static  String SERVER_ADDRESS = "http://114.110.8.114/api-mobile/api/";
    public static String SERVER_ADDRESS = "http://192.168.1.252:9401/api-mobile/api/";
    /**
     * webview加载超时时间设置
     */
    public static final int TIME_OUT = 15000;
    /**
     * volley超时时间设置
     */
    public static final int VOLLEY_TIME_OUT = 15000;
    /**
     * 下拉刷新控件颜色集合
     */
    public static int[] REFRESH_COLORS ={android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light};

    /**
     * 错误日志存储路径
     * @param context
     * @return
     */
    public static String getErrLogSDAddress(Context context) {
        //sd/PeoHosp/download/njztc_normal.apk
        String address= Environment.getExternalStorageDirectory()+"/"+Constants.SD_FILE_NAME+"/errlog/errlog.log";
        return address;
    }
}
