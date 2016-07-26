package com.zkr.jsw.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Map;

/**
 * @PackageName: ---------com.zkr.jsw.utils
 * @Description: ---------格式化
 * @author: ---------------LF
 * @date: -----------------2016/7/26 9:53
 * @Copyright: -----------中科软
 */
public class ParseJsonUtils {

    /**
     * @Description: -------格式化服务器返回的头部数据
     * @author --------------LF
     * @date ----------------2016/7/26 10:21
     */
    public static String getParseData(Context context,String json){
        Map<String, Object> obj;
        try {
            obj = JsonUtils.getMapObj(json);
            if (obj.get("success").toString().equals("0")) {
                Toast.makeText(context, obj.get("msg").toString(),Toast.LENGTH_SHORT).show();
            } else if (obj.get("success").toString().equals("1")) {
                return obj.get("data").toString();
            } else {
                Toast.makeText(context, "登录过期",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
        return null;
    }
}
