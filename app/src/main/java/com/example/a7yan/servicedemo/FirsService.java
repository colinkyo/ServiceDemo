package com.example.a7yan.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 7yan on 2016/12/30.
 */

public class FirsService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("7Yan","-----------------onBind-----------------------");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("7Yan","-----------------onUnbind-----------------------");
        return super.onUnbind(intent);
    }
    @Override
    public void onCreate() {
        Log.i("7Yan","-----------------onCreate-----------------------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("7Yan","-----------------onStartCommand-----------------------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("7Yan","-----------------onDestroy-----------------------");
        super.onDestroy();
    }
}
