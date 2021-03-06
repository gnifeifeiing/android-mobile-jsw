package cn.com.sinosoft.wjwapp.utils;

import android.content.Context;
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
    static Map<String, Object> obj;
    /**
     * @Description: -------格式化服务器返回的头部数据
     * @author --------------LF
     * @date ----------------2016/7/26 10:21
     */
    public static String getParseData(Context context,String json){
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
