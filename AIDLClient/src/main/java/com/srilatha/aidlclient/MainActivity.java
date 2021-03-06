package com.srilatha.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.srilatha.ClientSideExample.bean.UserBean;
import com.srilatha.ClientSideExample.ICustomAidlInterface;

import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private ICustomAidlInterface iCustomAidlInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCustomAidlInterface = ICustomAidlInterface.Stub.asInterface(service);
            Log.i(TAG, "onServiceConnected");
            Toast.makeText(MainActivity.this, "service connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
            iCustomAidlInterface = null;
        }
    };
    TextView tvTime;
    TextView tvUser;
    int cnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvTime = findViewById(R.id.tv_time);
        tvUser = findViewById(R.id.tv_user);
        findViewById(R.id.btn_bind).setOnClickListener(v -> {
            Intent intent = new Intent();
            //Server-side Action defined in AndroidManifest.xml
            intent.setAction("com.srilatha.aidl.remote");
            //After 5.0, the remote service cannot be bound through implicit Intent, and the package name needs to be set
            intent.setPackage("com.srilatha.ClientSideExample");
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        });
        findViewById(R.id.btn_time).setOnClickListener(v -> {
            if (iCustomAidlInterface != null) {
                try {
                    tvTime.setText(String.format("Client calls time\n???%s", iCustomAidlInterface.getCurrentTime()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_insert).setOnClickListener(v -> {
            if (iCustomAidlInterface != null) {
                cnt++;
                UserBean userBean = new UserBean();
                userBean.setName("Yishita Sai" + cnt);
                userBean.setAddress("Hyderabad" + cnt);
                userBean.setAge(cnt);
                try {
                    iCustomAidlInterface.insertUser(userBean);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_get).setOnClickListener(v -> {
            if (iCustomAidlInterface != null) {
                try {
                    List<UserBean> list = iCustomAidlInterface.getUsers();
                    tvUser.setText(list.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            if (iCustomAidlInterface != null) {
                try {
                    iCustomAidlInterface.clearUser();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iCustomAidlInterface != null) {
            unbindService(serviceConnection);
        }
    }
}
