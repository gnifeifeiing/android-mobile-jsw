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
 * @Description: ---------账户设置
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:29
 * @Copyright: -----------中科软
 */
public class SetAccountFragment extends Fragment {


    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.set_account_fragment_activity,null);
        return view;
    }
}
