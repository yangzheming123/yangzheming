package com.bytedance.videoplayer;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Environment;
import android.os.HandlerThread;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView textView1,textView2;
    private SurfaceView surfaceView;
    private VideoPlayerIJK ijkplayer;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private float max;
    private String path;
    private int count = 0;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textview1);
        textView2 = findViewById(R.id.textview2);
        surfaceView = findViewById(R.id.surfaceView);
        player = new MediaPlayer();

        /*
        ijkplayer = findViewById(R.id.ijkPlayer);
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        ijkplayer.setListener(new MyPreparedListener());
        ijkplayer.setVideoResource(R.raw.hearthstone);
        */

        /*path = Environment.getExternalStorageDirectory().getPath()+"/";
        File file = new File(path);
        if (!file.exists()) {//判断需要播放的文件路径是否存在，不存在退出播放流程
            Toast.makeText(this,"文件路径不存在",Toast.LENGTH_LONG).show();
            return;
        }*/
        /*final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(player.isPlaying()){
                    textView1.setText(player.getCurrentPosition()/60000+":"+player.getCurrentPosition()%60000/1000);
                    seekbar.setProgress(player.getCurrentPosition()*100/player.getDuration());
                    handler.postDelayed(runnable,1000);
                }
            }
        };*/
        try {
            player.setDataSource(getResources().openRawResourceFd(R.raw.bytedance));
            holder = surfaceView.getHolder();
            holder.addCallback(new PlayerCallBack());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.setLooping(false);
                    textView1.setText(player.getCurrentPosition()/60000+":"+player.getCurrentPosition()%60000/1000);
                    textView2.setText(player.getDuration()/60000+":"+(player.getDuration()/1000)%60);
                    handler.post(runnable);
                }
            });
            player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    count++;
                    changeVideoSize(mp);
                    handler.post(runnable);
                }
            });
            player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    System.out.println(percent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                handler.post(runnable);
            }
        });

        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying())
                    player.pause();
            }
        });

        seekbar = findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    player.seekTo(progress*player.getDuration()/100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            if(player != null){
                textView1.setText(player.getCurrentPosition()/60000+":"+player.getCurrentPosition()%60000/1000);
                seekbar.setProgress(player.getCurrentPosition()*100/player.getDuration());
                handler.postDelayed(runnable,1000);
            }
        }
    };

    public void changeVideoSize(MediaPlayer mediaPlayer) {
        int surfaceWidth = surfaceView.getWidth();
        int surfaceHeight = surfaceView.getHeight();

        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        /*
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
            videoWidth = (int) Math.ceil((float) videoWidth / max);
            videoHeight = (int) Math.ceil((float) videoHeight / max);
        } else {
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth / (float) surfaceHeight), (float) videoHeight / (float) surfaceWidth);
            videoWidth = (int) Math.ceil((float) videoWidth * max);
        }
        */
        videoWidth = surfaceWidth;
        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        if(count > 1){
            surfaceView.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));
        }

    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    private class PlayerCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    /*class MyPreparedListener implements IjkMediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            iMediaPlayer.start();
            textView2.setText(ijkplayer.getDuration()+"");
            Log.d("yzm",ijkplayer.getDuration()+"");
            seekbar.setMax((int)ijkplayer.getDuration());
            //发消息
            handler.postDelayed(runnable,1000);

        }
    }*/

}


