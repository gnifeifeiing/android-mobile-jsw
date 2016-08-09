package cn.com.sinosoft.wjwapp.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import cn.com.sinosoft.wjwapp.MyApplication;
import cn.com.sinosoft.wjwapp.utils.OptsharepreInterface;
import cn.com.sinosoft.wjwapp.utils.UncaughtException;


/**
 * Created by lenovo on 2016/3/16.
 */
public abstract class BaseActivity extends FragmentActivity {
    public static String TAG = "";
    public OptsharepreInterface share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        MyApplication app=MyApplication.getInstance();
        app.addActivity(this);
        //全局异常捕获
        UncaughtException.getInstance().setContext(this);
        share=new OptsharepreInterface(this);
        TAG = this.getClass().getName();
    }


    @Override
    protected void onDestroy() {

        MyApplication.getInstance().finishActivity(this);
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
