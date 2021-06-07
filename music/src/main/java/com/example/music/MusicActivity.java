package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        //服务的启动和停止
        final Intent intent  = new Intent(MusicActivity.this,MyService.class);
        ImageButton imageButton = findViewById(R.id.play);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyService.isPlay == false){//若音乐没有播放，则启动服务，修改图标
                    startService(intent);//启动服务
                    ((ImageButton)v).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.play));
                }else{//否则停止服务，修改图标
                    stopService(intent);
                    ((ImageButton)v).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.stop));
                }
            }
        });
    }
    //设置音乐自动播放

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(MusicActivity.this,MyService.class));
    }
}
