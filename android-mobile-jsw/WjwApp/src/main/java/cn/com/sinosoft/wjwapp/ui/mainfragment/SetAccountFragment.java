package cn.com.sinosoft.wjwapp.ui.mainfragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.com.sinosoft.wjwapp.R;
import cn.com.sinosoft.wjwapp.update.UpdateVersionUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @PackageName: ---------com.zkr.jsw.ui.mainfragment
 * @Description: ---------账户设置
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:29
 * @Copyright: -----------中科软
 */
public class SetAccountFragment extends Fragment {


    View view;
    @Bind(R.id.btn_update)
    Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.set_account_fragment_activity, null);
        ButterKnife.bind(this, view);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json=   " {'success':'1','msg':'成功','data':{'id':4,'version':'3','versionName':'CH_BXYY_and_pub_V0.1.00', 'content':'百姓医院新版本发布了。', 'download':'http://114.110.8.114/appointdownload/android/CH_BXYY_and_pub_V0.1.00.apk', 'createTime':1468209325000, 'updateTime':1468209366000}}";
                String path= Environment.getExternalStorageDirectory()+"/jsw/download/";
                UpdateVersionUtils update=new UpdateVersionUtils(getActivity(),json,path,"jsw.apk");
                update.setAppIcon(R.mipmap.ic_launcher);
                update.setAppName("计生委");
                update.showDialog();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
