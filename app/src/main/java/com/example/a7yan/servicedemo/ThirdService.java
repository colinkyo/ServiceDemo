package com.example.a7yan.servicedemo;

import android.app.IntentService;
import android.content.Intent;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ThirdService extends IntentService {
    //必须有
    public ThirdService()
    {
        super("ThirdService");
    }
    //处理意图的方法
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            URL url=new URL("http://pic.dbw.cn/0/08/44/30/8443021_985878.jpg");
            //打开连接
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if(conn.getResponseCode()==200){
                InputStream is=conn.getInputStream();
                //创建字节输出流
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                int len=0;
                byte[] buffer=new byte[1024];
                while ((len=is.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                }
                //发送一个广播
                Intent intent1=new Intent();
                //设置广播标记
                intent1.setAction("download");
                //绑定广播数据
                intent1.putExtra("img",baos.toByteArray());
                sendBroadcast(intent1);

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
