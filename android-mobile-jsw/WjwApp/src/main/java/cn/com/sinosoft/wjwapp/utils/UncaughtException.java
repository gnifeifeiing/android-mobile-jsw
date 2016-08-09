package cn.com.sinosoft.wjwapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;

import cn.com.sinosoft.wjwapp.MyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:     捕获全局异常,因为有的异常我们捕获不到
 * User:         LF
 * Date:         2016/4/22 11:35
 */
public class UncaughtException implements UncaughtExceptionHandler {
    private final static String TAG = "UncaughtException";
    private static UncaughtException mUncaughtException;
    private Context context;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private UncaughtException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 同步方法，以免单例多线程环境下出现异常
     */
    public synchronized static UncaughtException getInstance() {
        if (mUncaughtException == null) {
            mUncaughtException = new UncaughtException();
        }
        return mUncaughtException;
    }

    /**
     * 初始化，把当前对象设置成UncaughtExceptionHandler处理器
     */
    public void init(Context context) {
        this.context=context;
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtException);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // TODO Auto-generated method stub
        //处理异常,我们还可以把异常信息写入文件，以供后来分析。
        saveCrashInfo2File(ex);
//        Log.e(TAG, "uncaughtException thread : " + thread + "||name=" + thread.getName() + "||id=" + thread.getId() + "||exception=" + ex);
//        new Thread() {
//            public void run() {
//                Looper.prepare();
//                showDialog();
//                Looper.loop();
//            }
//
//        }.start();

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("提示")
                .setMessage("系统错误，请上传日志")
                .setPositiveButton("我知道了",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                MyApplication.getInstance().ExitApp();
                            }
                        });

        AlertDialog dialog = builder.create();
        //需要的窗口句柄方式，没有这句会报错的
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }

    /**
     * 保存错误信息到文件中
     *
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();

        long timestamp = System.currentTimeMillis();
        String time = formatter.format(new Date());
        sb.append("\r\n" + time + "\r\n");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\r\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory()+"/"+Constants.SD_FILE_NAME+"/errlog";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(Constants.getErrLogSDAddress(context), true);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }

    }
}
