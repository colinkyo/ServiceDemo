package com.example.a7yan.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class FourthService extends Service {
    public FourthService() {
    }
    //IBinder：接口，用来在不同的组件或者进程中进行信息传递的
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("7Yan","_________________onBind______________________");
        // TODO: Return the communication channel to the service.
        //这里必须有返回对象，IDE自动生成抛出异常，注意处理
        MyBinder myBinder=new MyBinder();
        return myBinder;
    }
    //获取100以内的随机数
    public int getRandomValue(){
        return new Random().nextInt(100);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("7Yan","__________________onStartCommand_____________________");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.i("7Yan","____________________onCreate___________________");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("7Yan","___________________onUnbind____________________");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("7Yan","____________________onDestroy___________________");
        super.onDestroy();
    }
    class MyBinder extends Binder{
        public FourthService getInstance()
        {
            return FourthService.this;
        }
    }
}
