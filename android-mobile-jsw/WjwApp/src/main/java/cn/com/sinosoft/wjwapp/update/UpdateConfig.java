package cn.com.sinosoft.wjwapp.update;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import java.io.File;

/**
 * @PackageName: ---------com.zkr.jsw.update
 * @Description: ---------版本更新工具类
 * @author: ---------------LF
 * @date: -----------------2016/7/15 11:46
 * @Copyright: -----------中科软
 */
public class UpdateConfig {
    /**
     * 获取版本号（PS：1）
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }

    /**
     * 获取版本名称（PS：1.0.2）
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;

    }

    /**
     * strings.xml
     * @param context
     * @return
     */
    public static String getAppName(Context context,int appName) {
        String verName = context.getResources().getText(appName)
                .toString();
        return verName;
    }

    /**
     *
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(ipInt & 0xFF).append(".");
            sb.append((ipInt >> 8) & 0xFF).append(".");
            sb.append((ipInt >> 16) & 0xFF).append(".");
            sb.append((ipInt >> 24) & 0xFF);
        } catch (Exception e) {
            return String.valueOf(ipInt);
        }
        return sb.toString();
    }

    /**
     *
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " " + ex.getMessage();
        }
    }

    /**
     * Describe:     判断是否已下载，若已下载，侧检测是否最新版本
     * User:         LF
     * Date:         2016/4/12 14:32
     */
    public static boolean getUninstallAPKInfo(Context context,
                                              int newVerCode,String filePath,String fileName) {
        String archiveFilePath = filePath+fileName;

        int verCode = 0;
        PackageManager pm = context.getPackageManager();
        PackageInfo pakinfo = pm.getPackageArchiveInfo(archiveFilePath,
                PackageManager.GET_ACTIVITIES);
        if (pakinfo != null) {
            verCode = pakinfo.versionCode;
        }
        if (newVerCode == verCode) {
            return true;
        }
        return false;
    }

    /**
     * Describe:    	已经下载过安装包的安装提示框
     * User:         LF
     * Date:         2016/4/12 14:34
     */
    public static void showInstallDialog(final Context currentContext, final String filePath, final String fileName) {

        final AlertDialog.Builder nickbuilder = new AlertDialog.Builder(currentContext);
        nickbuilder.setMessage("已下载过最新版本，是否立即安装？");
        nickbuilder.setTitle("软件更新");
        nickbuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        nickbuilder.setPositiveButton("安装", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(filePath+fileName)),
                        "application/vnd.android.package-archive");
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                currentContext.startActivity(intent);
                dialog.dismiss();
            }
        });
        nickbuilder.create().show();
    }
}
