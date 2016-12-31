package com.example.a7yan.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnopen;
    private Button btnclose;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void open(View view){
        intent=new Intent(this,FirsService.class);
        startService(intent);
    }
    public void close(View view){
        stopService(intent);
    }
    public void jump(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.btn_jump:
                intent=new Intent(this,SecondActivity.class);
                break;
            case R.id.btn_jump1:
                intent=new Intent(this,ThirdActivity.class);
                break;
            case R.id.btn_jump2:
                intent=new Intent(this,FourthActivity.class);
                break;
        }
        startActivity(intent);
    }
}
