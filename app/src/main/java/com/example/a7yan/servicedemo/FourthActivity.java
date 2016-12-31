package com.example.a7yan.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FourthActivity extends AppCompatActivity {
    private Button btnopen,btnclose;
    private MyConn conn;
    private FourthService.MyBinder myBinder;
    private FourthService fourthService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }
    public void bindService(View view){
        Intent intent =new Intent(this,FourthService.class);
        //conn 当服务被开启或者停止的时，用来接受信息
        conn=new MyConn();
        bindService(intent,conn,BIND_AUTO_CREATE);
    }
    public void unbindService(View view){
        if(conn!=null) {
            //要选对 ServiceConnection的类，不是view 类
           unbindService(conn);
            conn=null;
        }
    }

    @Override
    protected void onDestroy() {
        if(conn!=null) {
            //要选对 ServiceConnection的类，不是view 类
            unbindService(conn);
            conn=null;
        }
        super.onDestroy();
    }

    //自定义的服务接口方法
    class MyConn implements ServiceConnection
    {
        //当服务建立链接时调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            //由FourthService的onBind传回
            myBinder= (FourthService.MyBinder) iBinder;
            fourthService=myBinder.getInstance();
            Log.i("7Yan","------------------------onServiceConnected----------------------");
        }
        //当服务失去链接时调用，当服务是因为一些意外原因，如内存严重不如，程序崩溃等
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
    public void  getValue(View view){
        if(fourthService!=null) {
            int value = fourthService.getRandomValue();
            Toast.makeText(this, "获取到的随机数:" + value, Toast.LENGTH_SHORT).show();
        }
    }
}
