package com.zkr.jsw.ui.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.zkr.jsw.MyApplication;
import com.zkr.jsw.R;
import com.zkr.jsw.utils.Constants;
import com.zkr.jsw.utils.ParseJsonUtils;
import com.zkr.jsw.widget.IStringRequest;

/**
 * @PackageName: ---------com.zkr.jsw.ui.mainfragment
 * @Description: ---------消息推送
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:28
 * @Copyright: -----------中科软
 */
public class MsgPushFragment extends Fragment implements View.OnClickListener {

    View view;
    Button btn,btn2;
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.msg_push_fragment_activity,null);
        init();
        return view;
    }

    private void init() {
        btn= (Button) view.findViewById(R.id.btn);
        btn2= (Button) view.findViewById(R.id.btn2);
        tv= (TextView) view.findViewById(R.id.tv);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                IStringRequest request=new IStringRequest(getActivity(), Request.Method.GET,"arrayJob/default", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result=ParseJsonUtils.getParseData(getActivity(),response);
                        if(result!=null){
                            tv.setText(result);
                        }else{
                            tv.setText("no data");
                        }
                    }
                });
                MyApplication.getSingleQueue().add(request);
                break;
            case R.id.btn2:
                break;
        }

    }
}
