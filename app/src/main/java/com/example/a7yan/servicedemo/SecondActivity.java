package com.example.a7yan.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private Button btndownload;
    private ImageView iv;
    private MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btndownload = (Button) findViewById(R.id.btn_download);
        iv = (ImageView) findViewById(R.id.iv);
        //注册广播接受器，动态注册法
        myReceiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("download");
        registerReceiver(myReceiver,filter);
    }
    public void download(View view)
    {
        Intent service=new Intent(this,SecondService.class);
        startService(service);
    }
    //接收广播
    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] datas=intent.getByteArrayExtra("img");
            Bitmap bitmap=BitmapFactory.decodeByteArray(datas,0,datas.length);
            iv.setImageBitmap(bitmap);
        }

    }
    //取消广播接受器
    @Override
    protected void onDestroy() {
        if(myReceiver!=null){
         unregisterReceiver(myReceiver);
        }
        super.onDestroy();
    }
}
