package cn.com.sinosoft.wjwapp.ui.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.sinosoft.wjwapp.R;


/**
 * @PackageName: ---------com.zkr.jsw.ui.mainfragment
 * @Description: ---------动态展示
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:27
 * @Copyright: -----------中科软
 */
public class ShowFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.show_fragment_activity,null);
        return view;
    }
}
