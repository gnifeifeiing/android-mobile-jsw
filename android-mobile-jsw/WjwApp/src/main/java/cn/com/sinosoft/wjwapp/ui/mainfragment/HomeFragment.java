package cn.com.sinosoft.wjwapp.ui.mainfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.wjwapp.R;


/**
 * @PackageName: ---------com.zkr.jsw.ui.mainfragment
 * @Description: ---------首页
 * @author: ---------------LF
 * @date: -----------------2016/6/28 11:25
 * @Copyright: -----------中科软
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    View view;
    PullToRefreshListView listview;
    List<String> lists = new ArrayList<String>();

    private MyAdapter adapter;

    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                listview.onRefreshComplete();
            }else{
                getData();
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_frament_activity,null);
        getData();
        init();
        return view;
    }

    private void init() {
        listview=(PullToRefreshListView)view.findViewById(R.id.mrjp_lv);
        ILoadingLayout startLabels = listview
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = listview.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        adapter=new MyAdapter(getActivity());
        listview.setAdapter(adapter);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }

    private List<String> getData(){
        for(int i = 0;i < 20;i ++){
            lists.add("item " + i + " 搜索业务增速下滑 Google廉颇老矣?");
        }
        return lists;
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder viewHolder = null;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.home_list_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(lists.get(position));

            return convertView;
        }

        class ViewHolder{
            TextView title;
        }
    }


    @Override
    public void onClick(View v) {

    }
}
