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

public class ThirdActivity extends AppCompatActivity {
    private ImageView iv;
    private Button btndownload;
    private MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        iv = (ImageView) findViewById(R.id.iv);
        btndownload = (Button) findViewById(R.id.btn_download);
        //动态注册广播器
        myReceiver=new MyReceiver();
        //设置广播标记
        IntentFilter filter=new IntentFilter();
        filter.addAction("download");
        registerReceiver(myReceiver,filter);
    }
    public void download(View view){
        Intent service =new Intent(this,ThirdService.class);
        startService(service);
    }
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] datas=intent.getByteArrayExtra("img");
            Bitmap bm=BitmapFactory.decodeByteArray(datas,0,datas.length);
            iv.setImageBitmap(bm);
        }
    }

    @Override
    protected void onDestroy() {
        if(myReceiver!=null){
            unregisterReceiver(myReceiver);
        }
        super.onDestroy();
    }
}
