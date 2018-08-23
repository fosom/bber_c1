package com.bber.company.android.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bber.company.android.R;
import com.bber.company.android.app.AppManager;
import com.bber.company.android.app.MyApplication;
import com.bber.company.android.databinding.ActivityVideoViewBinding;
import com.bber.company.android.util.CLog;
import com.bber.company.android.view.customcontrolview.DialogView;
import com.bber.company.android.widget.AutoMarqueeTextView;
import com.bber.company.android.widget.FullScreenVideoView;
import com.bber.company.android.widget.VideoErrorDialog;

import java.io.IOException;

/**
 * Created by bn on 2017/9/15.
 */

public class LandscapeActivity extends BaseActivity implements View.OnClickListener {

    private ActivityVideoViewBinding binding;
    private RelativeLayout video_rela;
    private FullScreenVideoView videoView;
    private ProgressBar mp4_progressBar;
    private TextView btn_close, change_screen;
    private AutoMarqueeTextView videoname;
    private MediaController mediaController;
    private SensorManager mSensorManager;
    private SensorListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_view);
        binding.setHeaderBarViewModel(headerBarViewModel);
        MyApplication.getContext().allActivity.add(this);
        AppManager.getAppManager().addActivity(this);
        initView();
    }

    private void initView() {
        video_rela = binding.videoGroup;
        videoView = binding.videoView;
        mp4_progressBar = binding.mp4ProgressBar;
        btn_close = binding.btnClose;
        change_screen = binding.changeScreen;
        videoname = findViewById(R.id.voice_name_id);
        Intent intent = getIntent();
        String strurl = intent.getStringExtra("url");
        //strurl = "http://150.95.141.131/hancle_crystal.mp4";
        CLog.i("video URL = "+ strurl);

        String videonam = intent.getStringExtra("name");

        if (!TextUtils.isEmpty(videonam)) {
            videoname.setText(videonam);
        }

        Uri uri = Uri.parse(strurl);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
        videoView.seekTo(0);

        videoView.setOnCompletionListener(completionListener);
        videoView.setOnPreparedListener(preparedListener);
        videoView.setOnErrorListener(errorListener);
        btn_close.setOnClickListener(this);
        change_screen.setOnClickListener(this);

        mediaController = new MediaController(LandscapeActivity.this);
        videoView.setMediaController(mediaController);

        DialogView.show(LandscapeActivity.this, true);

    }

    @Override
    public void setHeaderBar() {

    }

    /**
     * 获取屏幕的状态
     *
     * @param context
     * @return
     */
    public boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    //video播放完毕
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            if (!isScreenOriatationPortrait(LandscapeActivity.this)) {// 当屏幕是横屏时
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置当前activity为竖屏
            }

        }
    };

    //video准备工作监听
    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            videoView.setVideoWidth(mediaPlayer.getVideoWidth());
            videoView.setVideoHeight(mediaPlayer.getVideoHeight());
            // mp4_progressBar.setVisibility(View.GONE);
            DialogView.dismiss(LandscapeActivity.this);
        }
    };

    MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {

            DialogView.dismiss(LandscapeActivity.this);
            try {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.video_error_alertdialog, null, true);

                final VideoErrorDialog videoErrorDialog = new VideoErrorDialog(LandscapeActivity.this, view);

                view.findViewById(R.id.button_sure_id).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        videoErrorDialog.dismiss();
                        finish();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    };
    private boolean isPlayFlag = false;

    /**
     * 停止播放视频
     */
    public void stopVideoPlaying() {
        videoView.pause();
        videoView.stopPlayback();
        if (!isScreenOriatationPortrait(LandscapeActivity.this)) {// 当屏幕是横屏时
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置当前activity为竖屏
        }
        finish();
    }

    private boolean isPortrait = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                stopVideoPlaying();
                break;
            case R.id.change_screen:
                if (isPortrait) {   // 当屏幕是竖屏时
                    // 点击后变横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 设置当前activity为横屏
                    isPortrait = false;
                    videoname.setVisibility(View.GONE);
                } else {
                    isPortrait = true;
                    videoname.setVisibility(View.VISIBLE);

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置当前activity为竖屏
                    //显示其他组件
                }
                break;
        }
    }
}
