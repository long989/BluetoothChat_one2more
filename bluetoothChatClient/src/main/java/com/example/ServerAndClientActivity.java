package com.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bluetoothchatclient.R;
import com.example.bluetoothchatcore.Constants;
import com.example.bluetoothclient.BluetoothChatFragment;
import com.example.bluetoothserver.BluetoothChatServerFragment;
import com.example.event.ClientMessageEvent;
import com.example.event.ServerMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author : qiukailong
 * date   : 2020/8/25  2:34 PM
 * desc   :
 */
public class ServerAndClientActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] titles = new String[]{"客户端", "服务端"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private BluetoothChatFragment mClientFragment;
    private BluetoothChatServerFragment mServerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main2);
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.view_pager);
        initView();
    }

    private void initView() {
        mClientFragment = new BluetoothChatFragment();
        mServerFragment = new BluetoothChatServerFragment();
        fragmentList.add(mClientFragment);
        fragmentList.add(mServerFragment);

        mTabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(new CustomFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void acceptServerMsg(ServerMessageEvent event) {
        if (event.getMessageRead() == Constants.MESSAGE_READ) {
            mClientFragment.acceptServerMsg(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void acceptClientMsg(ClientMessageEvent event) {
        if (event.getMessageRead() == Constants.MESSAGE_READ) {
            mServerFragment.acceptClientMsg(event);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class CustomFragmentAdapter extends FragmentPagerAdapter {
        private String[] titles = new String[]{"客户端", "服务端"};
        private List<Fragment> fragmentList = new ArrayList<>();

        public CustomFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            if (fragments == null) {
                fragmentList = new ArrayList<>();
            } else {
                fragmentList = fragments;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
