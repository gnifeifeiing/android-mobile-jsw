package cn.com.sinosoft.wjwapp.update;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import cn.com.sinosoft.wjwapp.utils.JsonUtils;

import java.util.Map;

/**
 * @PackageName: ---------com.zkr.jsw.update
 * @Description: ---------版本更新工具类
 * @author: ---------------LF
 * @date: -----------------2016/7/15 11:24
 * @Copyright: -----------中科软
 */
public class UpdateVersionUtils {

    private Context mContext;
    private String versionJson;//服务端版本json串
    //{"success":"1","msg":"成功","data":
    // {"id":4,
    // "version":"0",
    // "versionName":"CH_BXYY_and_pub_V0.1.00",
    // "content":"百姓医院新版本发布了。",
    // "download":"http://114.110.8.114/appointdownload/android/CH_BXYY_and_pub_V0.1.00.apk",
    // "createTime":1468209325000,
    // "updateTime":1468209366000}}
    private Map<String, Object> serverObject;//json对象
    private int currVerCode;//当前应用版本号
    private String filePath,fileName;

    private int appIcon;//图标
    private String appName;//app名称

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    public UpdateVersionUtils(Context context, String versionJson, String filePath, String fileName){
        this.mContext=context;
        this.versionJson=versionJson;
        this.currVerCode=UpdateConfig.getVerCode(context);
        this.filePath=filePath;
        this.fileName=fileName;
    }
    /**
     * @Description: -------服务端数据是否获取成功
     * @author --------------LF
     * @date ----------------2016/7/15 11:37
     */
    private boolean isGetServerData() {
        try {
            serverObject = JsonUtils.getMapObj(versionJson);
            if (serverObject.get("success").toString().equals("0")) {
                return false;
            } else if(serverObject.get("success").toString().equals("1")) {
                serverObject= JsonUtils.getMapObj(serverObject.get("data").toString());
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * @Description: -------是否更新
     * @author --------------LF
     * @date ----------------2016/7/15 11:37
     */
    private boolean isUpdate() {
        if (isGetServerData()) {
            int newVerCode=Integer.parseInt(serverObject.get("version").toString());//获取服务器存储的版本号
            if (newVerCode > currVerCode) {
                return true;
            }
        }
        return false;
    }

    public void showDialog(){
        if(isUpdate()){//需要更新
            StringBuffer sb = new StringBuffer();
            sb.append("检测到新版本:");
            sb.append(serverObject.get("versionName").toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            sb.append(",\n更新内容：\n" + serverObject.get("content").toString());
            builder.setMessage(sb.toString());
            builder.setTitle("软件更新");
            builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (UpdateConfig.getUninstallAPKInfo(mContext, Integer.parseInt(serverObject.get("version").toString()),filePath,fileName)) {
                        UpdateConfig.showInstallDialog(mContext,filePath,fileName);
                    } else {
                        // 启动下载安装任务
                        boolean isupLocStart = NetworkTool
                                .isServiceRunning(mContext,
                                        UpdateService.class.getName());
                        if (isupLocStart) {
                            Toast.makeText(mContext,"正在更新...",Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(mContext,
                                    UpdateService.class);
                            intent.putExtra("download", serverObject.get("download").toString());
                            intent.putExtra("appicon", appIcon);
                            intent.putExtra("appname", appName);
                            intent.putExtra("filepath", filePath);
                            intent.putExtra("filename", fileName);
                            mContext.startService(intent);
                        }
                        dialog.dismiss();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else{//不需要更新

        }
    }
}
