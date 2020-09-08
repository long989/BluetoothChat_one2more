package com.example.bluetoothserver;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.bluetoothchatclient.R;

import java.lang.reflect.Method;

/**
 * author : qiukailong
 * date   : 2020/8/25  2:05 PM
 * desc   :
 */
public class ServerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        setDiscoverableTimeout(100);
    }

    public void setDiscoverableTimeout(int timeout) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
            setScanMode.setAccessible(true);
            setDiscoverableTimeout.invoke(adapter, timeout);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, timeout);
        } catch (Exception e) {
            Log.d("test", "setDiscoverableTimeout: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
