package com.zkr.jsw;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkr.jsw.base.BaseActivity;
import com.zkr.jsw.ui.mainfragment.HomeFragment;
import com.zkr.jsw.ui.mainfragment.MsgPushFragment;
import com.zkr.jsw.ui.mainfragment.SetAccountFragment;
import com.zkr.jsw.ui.mainfragment.ShowFragment;
/**
 * @Description: -------首页父activity
 * @author --------------LF
 * @date ----------------2016/6/29 9:39
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Fragment mHomeFragment;
    private Fragment mShowFragment;
    private Fragment mMsgPushFragment;
    private Fragment mSetAccountFragment;
    private ImageView image1, image2, image3, image4;
    private TextView textView1, textView2, textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        image1 = (ImageView) findViewById(R.id.tab_home_image);
        image2 = (ImageView) findViewById(R.id.tab_show_image);
        image3 = (ImageView) findViewById(R.id.tab_msg_push_image);
        image4 = (ImageView) findViewById(R.id.tab_set_account_image);
        textView1 = (TextView) findViewById(R.id.tab_home_text);
        textView2 = (TextView) findViewById(R.id.tab_show_text);
        textView3 = (TextView) findViewById(R.id.tab_msg_push_text);
        textView4 = (TextView) findViewById(R.id.tab_set_account_text);
        LinearLayout l1 = (LinearLayout) findViewById(R.id.tab_home);
        LinearLayout l2 = (LinearLayout) findViewById(R.id.tab_show);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.tab_msg_push);
        LinearLayout l4 = (LinearLayout) findViewById(R.id.tab_set_account);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        select(0);
        if (MyApplication.loginFlag){

        }
        if (getIntent().getIntExtra("postion", 0) != 0) {
            select(getIntent().getIntExtra("postion", 0));
        } else if (getIntent().getIntExtra("postion", 1) == 0) {
            select(0);
        }
    }

    /**
     * @Description:        显示对应得fragment
     * @author               miao
     * @date                 2016/4/28 11:15
     */
    private void select(int position) {
        // 获取FragmentManager对象
        // FragmentManager fm=getFragmentManager();
        FragmentManager fm = getSupportFragmentManager();
        // 获取事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏事务
        hideFragment(transaction);
        // 根据传递过来的参数 选择显示对应的Fragment
        switch (position) {
            case 0:// HomeFragment
                // 如果此Fragment为空 就新建一个
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.cccc, mHomeFragment);
                } else {// 此Fragment已经存在 直接显示
                    transaction.show(mHomeFragment);
                }
                // 改变按下后图片和文字的状态
                changeStatus(0);
                break;
            case 1:
                if (mShowFragment == null) {
                    mShowFragment = new ShowFragment();

                    transaction.add(R.id.cccc, mShowFragment);
                } else {
                    transaction.show(mShowFragment);
                }
                changeStatus(1);
                break;
            case 2:
                if (mMsgPushFragment == null) {
                    mMsgPushFragment = new MsgPushFragment();
                    transaction.add(R.id.cccc, mMsgPushFragment);
                } else {
                    transaction.show(mMsgPushFragment);
                }
                changeStatus(2);
                break;
            case 3:
                if (mSetAccountFragment == null) {
                    mSetAccountFragment = new SetAccountFragment();
                    transaction.add(R.id.cccc, mSetAccountFragment);
                } else {
                    transaction.show(mSetAccountFragment);
                }
                changeStatus(3);
                break;
            default:
                break;
        }
        // 提交事务
        transaction.commit();
    }

    /**
     * @Description:        更换不同的fragment
     * @author               miao
     * @date                 2016/4/28 11:14
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        if (mMsgPushFragment != null) {
            transaction.hide(mMsgPushFragment);
        }
        if (mSetAccountFragment != null) {
            transaction.hide(mSetAccountFragment);
        }
    }

    /**
     * @Description:        改变UI
     * @author               miao
     * @date                 2016/4/28 11:15
     */
    private void changeStatus(int position) {

        // 重置所有图片
        image1.setImageResource(R.mipmap.ic_launcher);
        image2.setImageResource(R.mipmap.ic_launcher);
        image3.setImageResource(R.mipmap.ic_launcher);
        image4.setImageResource(R.mipmap.ic_launcher);
        // 重置所有文本的颜色
        textView1.setTextColor(Color.parseColor("#999999"));
        textView2.setTextColor(Color.parseColor("#999999"));
        textView3.setTextColor(Color.parseColor("#999999"));
        textView4.setTextColor(Color.parseColor("#999999"));
        // 改变对应图片和文本颜色
        switch (position) {
            case 0:
                image1.setImageResource(R.mipmap.ic_launcher);
                textView1.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 1:
                image2.setImageResource(R.mipmap.ic_launcher);
                textView2.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 2:
                image3.setImageResource(R.mipmap.ic_launcher);
                textView3.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 3:
                image4.setImageResource(R.mipmap.ic_launcher);
                textView4.setTextColor(Color.parseColor("#2fad68"));
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_home:
                select(0);
                break;
            case R.id.tab_show:
                select(1);
                break;
            case R.id.tab_msg_push:
                select(2);
                break;
            case R.id.tab_set_account:
                select(3);
                break;
            default:
                break;
        }
    }


    long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 2000) {
                time = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            } else {
                // finish();
                MyApplication.getInstance().ExitApp();
            }
        }
        return true;
    }
}
