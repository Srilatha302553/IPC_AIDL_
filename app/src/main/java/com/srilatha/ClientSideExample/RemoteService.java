package com.srilatha.ClientSideExample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.srilatha.ClientSideExample.bean.UserBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    private List<UserBean> list = new ArrayList<>();
    ICustomAidlInterface.Stub stub = new ICustomAidlInterface.Stub() {

        @Override
        public String getCurrentTime() throws RemoteException {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        }

        @Override
        public void insertUser(UserBean userBean) throws RemoteException {
            list.add(userBean);
        }

        @Override
        public List<UserBean> getUsers() throws RemoteException {
            return list;
        }

        @Override
        public void clearUser() throws RemoteException {
            list.clear();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        Toast.makeText(this, "Remote service onCreate\n", Toast.LENGTH_SHORT).show();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        Toast.makeText(this, "\n" +
                "Remote service onBind", Toast.LENGTH_SHORT).show();
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        Toast.makeText(this, "Remote service onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        Toast.makeText(this, "Remote service onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}