package com.example.calculationtest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    static boolean isPlay;  //isPlay用于记录播放状态   static类名.方法
    MediaPlayer player;//用于播放音乐

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //重写一些方法-回调的方法

    //创建服务时回调的方法

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.music);
    }
    //在启动服务的时候回调的方法

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!player.isPlaying()) {//若音乐没有播放，则播放音乐，同时记录音乐播放状态
            player.start();//播放音乐
            player.setLooping(true);//重复播放
            isPlay = player.isPlaying();//记录状态
        }
        return super.onStartCommand(intent, flags, startId);
    }
    //在停止服务的时候回调的方法

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止音乐的播放
        player.stop();
        //记录状态
        isPlay = player.isPlaying();
        //释放资源
        player.release();
    }
}
