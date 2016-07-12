package com.zkr.jsw.ui.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkr.jsw.R;

/**
 * @PackageName: ---------com.zkr.jsw.ui.mainfragment
 * @Description: ---------消息推送
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:28
 * @Copyright: -----------中科软
 */
public class MsgPushFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.msg_push_fragment_activity,null);
        return view;
    }
}
