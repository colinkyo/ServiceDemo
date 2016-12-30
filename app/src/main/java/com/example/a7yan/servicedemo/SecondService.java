package com.example.a7yan.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondService extends Service {
    public SecondService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new Thread(){
            //开启线程，进行耗时操作
            @Override
            public void run() {
                try {
                    URL url=new URL("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1483113386&di=84293010380355b478b8365d16c1dc9b&src=http://d.hiphotos.baidu.com/image/pic/item/5882b2b7d0a20cf482c772bf73094b36acaf997f.jpg");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    if(conn.getResponseCode()==200){
                        //下载结果
                        InputStream is=conn.getInputStream();
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        int len=0;
                        byte[] buffer=new byte[1024];
                        while ((len=is.read(buffer))!=-1){
                            //inputstream的内容写到输出流中
                            baos.write(buffer,0,len);
                        }
                        //发送广播
                        Intent intent1=new Intent();
                        //设置广播标记与SecondActivity的广播接收器的标记对应
                        intent1.setAction("download");
                        intent1.putExtra("img",baos.toByteArray());
                        sendBroadcast(intent1);
                        //完成操作后自己关闭,不然一直占后台
                        stopSelf();
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
