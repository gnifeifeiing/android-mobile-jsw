package cn.com.sinosoft.wjwapp.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Describe:     sharepreference属性存储
 * User:         LF
 * Date:         2016/3/18 16:32
 */
public class OptsharepreInterface {

    private SharedPreferences settings; // static

    public OptsharepreInterface(Context context) {
        // 载入配置文件
        settings = context.getSharedPreferences(Constants.SHARE_FILES,
                Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor() {
        return settings.edit();
    }


    public void putPres(String optName, String values) {
        SharedPreferences.Editor editor = settings.edit();
        if (optName.equals("guid")) {
            editor.putString("guid", values);// 登录人guid
        } else if (optName.equals("account")) {
            editor.putString("account", values);// 登录账号
        } else if (optName.equals("name")) {
            editor.putString("name", values);// 登录用户名
        }else if (optName.equals("loginFlag")) {
            editor.putString("loginFlag", values);// 登录状态
        } else if (optName.equals("password")) {
            editor.putString("password", values);// 登录密码
        } else if (optName.equals("phonenumber")) {
            editor.putString("phonenumber", values);// 手机号
        } else if (optName.equals("isFirstLogin")) {
            editor.putString("isFirstLogin", values);// 是否首次登陆(0:是	1:否)
        } else if (optName.equals("token")) {
            editor.putString("token", values);//登录成功后返回的token值
        }  else {
            editor.putString(optName, values);
        }
        editor.commit();
    }

    public String getPres(String optName) {
        String values = "";
        if (optName.equals("guid")) {// 获取登陆人guid
            values = settings.getString("guid", "");
        } else if (optName.equals("account")) {// 登录账号
            values = settings.getString("account", "0");
        } else if (optName.equals("password")) {// 登录密码
            values = settings.getString("password", "0");
        } else if (optName.equals("isFirstLogin")) {// 是否首次登陆
            values = settings.getString("isFirstLogin", "0");
        } else if (optName.equals("name")) {//登录用户名
            values = settings.getString("name", "");
        } else if (optName.equals("phonenumber")) {// 手机号
            values = settings.getString("phonenumber", "0");
        } else if (optName.equals("loginFlag")) {// 登录状态
            values = settings.getString("loginFlag", "");
        } else if (optName.equals("token")) {//登录成功后返回token值
            values = settings.getString("token", "");
        } else {
            values = settings.getString(optName, "");
        }
        // System.out.println("读取配置文件操作------" + optName + "---" + values);
        return values;
    }


    public boolean existResult(String result) {
        return settings.contains(result);
    }

    public void removePre(String preName) {
        // 必须马上提交，否则就删不了？？！
        settings.edit().remove(preName).commit(); // .commit()
        // settings.edit().commit();
    }

}
