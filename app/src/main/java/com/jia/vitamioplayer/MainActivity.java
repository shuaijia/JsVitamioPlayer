package com.jia.vitamioplayer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends Activity {

    private static final String TAG = "VitamioPlayer";

    private VideoView videoView;

    private JsMediaController mediaController;

    private String path = "http://baobab.wdjcdn.com/145076769089714.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //必须写这个，初始化加载库文件
        Vitamio.isInitialized(getApplicationContext());

        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        videoView.setVideoPath(path);
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mediaController = new JsMediaController(this, videoView, this);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        mediaController.setVideoName("尽享人生");

        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.e(TAG, "onPrepared: ");
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e(TAG, "onCompletion: ");
            }
        });

        videoView.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.e(TAG, "onSeekComplete: ");
            }
        });

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "onInfo: " + what);
                return false;
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "onError: " + what);
                return false;
            }
        });

        // 缓冲回调
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                Log.e(TAG, "onBufferingUpdate: "+percent );
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (videoView != null) {
            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

}
