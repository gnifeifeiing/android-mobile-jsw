package com.zkr.jsw.ui.mainfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.zkr.jsw.R;
import com.zkr.jsw.volley.MultipartEntity;
import com.zkr.jsw.volley.MultipartRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;


/**
 * @PackageName: ---------com.zkr.jsw.ui.mainfragment
 * @Description: ---------首页
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:25
 * @Copyright: -----------中科软
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    View view;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_frament_activity,null);
        init();
        return view;
    }

    private void init() {
        btn= (Button) view.findViewById(R.id.btn_upload);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        MultipartRequest multipartRequest = new MultipartRequest(
                "http://192.168.1.105:8080/", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("", "### response : " + response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("", "### error : " );
                Toast.makeText(getActivity(),"chucuo",Toast.LENGTH_SHORT).show();
            }
        });

        // 添加header
        multipartRequest.addHeader("header-name", "value");

        // 通过MultipartEntity来设置参数
        MultipartEntity multi = multipartRequest.getMultiPartEntity();
        // 文本参数
        multi.addStringPart("location", "模拟的地理位置");
        multi.addStringPart("type", "0");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 直接从上传Bitmap
        multi.addBinaryPart("images", bitmapToBytes(bitmap));
        // 上传文件
        String filePaht=Environment.getExternalStorageDirectory()+"/PeoHosp/images/160621161627.jpg";
        Log.e("asdfasdf",filePaht);
        File file=new  File("/storage/emulated/0/PeoHosp/images/160621161627.jpg");
        if(file.exists()){
            Log.e("asdfasdf","true");
        }else{
            Log.e("asdfasdf","false");
        }
        multi.addFilePart("imgfile", file);
        // 将请求添加到队列中
        queue.add(multipartRequest);
    }
    public byte[] bitmapToBytes(Bitmap bm) {
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                return baos.toByteArray();
             }
}
