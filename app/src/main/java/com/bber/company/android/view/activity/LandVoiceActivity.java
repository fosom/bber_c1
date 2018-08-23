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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bber.company.android.R;
import com.bber.company.android.app.AppManager;
import com.bber.company.android.app.MyApplication;
import com.bber.company.android.databinding.ActivityVoiceViewBinding;
import com.bber.company.android.view.customcontrolview.DialogView;
import com.bber.company.android.widget.AutoMarqueeTextView;
import com.bber.company.android.widget.FullScreenVideoView;
import com.bber.company.android.widget.VideoErrorDialog;
import com.bumptech.glide.Glide;

import java.io.IOException;

/**
 * Created by bn on 2017/9/15.
 */

public class LandVoiceActivity extends BaseActivity implements View.OnClickListener{

    private ActivityVoiceViewBinding binding;
    private RelativeLayout video_rela;
    private FullScreenVideoView videoView;
   // private ProgressBar mp4_progressBar;
    private TextView btn_close, change_screen;
    private AutoMarqueeTextView videoname;

    private MediaController mediaController;
    private SensorManager mSensorManager;
    private SensorListener mSensorListener;
    private ImageView background_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_voice_view);
        binding.setHeaderBarViewModel(headerBarViewModel);
        MyApplication.getContext().allActivity.add(this);
        AppManager.getAppManager().addActivity(this);
        initView();
    }

    private void initView() {
        video_rela = binding.videoGroup;
       videoView = findViewById(R.id.video_view);
       videoname = findViewById(R.id.voice_name_id);
        btn_close = binding.btnClose;
       change_screen = binding.changeScreen;
        background_img = (ImageView) findViewById(R.id.background_img);
        Intent intent = getIntent();
        String strurl = intent.getStringExtra("url");
        String source = intent.getStringExtra("source");
        String videonam = intent.getStringExtra("name");

        if (!TextUtils.isEmpty(videonam)) {
            videoname.setText(videonam);
        }
        Uri uri = Uri.parse(strurl);
        Glide.with(this).load(source).dontAnimate().into(background_img);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
        videoView.seekTo(0);

        videoView.setOnCompletionListener(completionListener);
        videoView.setOnPreparedListener(preparedListener);
        videoView.setOnErrorListener(errorListener);
        btn_close.setOnClickListener(this);
        change_screen.setOnClickListener(this);

        mediaController = new MediaController(LandVoiceActivity.this);
        videoView.setMediaController(mediaController);

        DialogView.show(LandVoiceActivity.this,true);
        //重力感应
//        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensorListener = new SensorEventListener() {
//
//            @Override
//            public void onSensorChanged(SensorEvent event) {
//                float[] values = event.values;
//                int orientation = ORIENTATION_UNKNOWN;
//                float X = -values[_DATA_X];
//                float Y = -values[_DATA_Y];
//                float Z = -values[_DATA_Z];
//                float magnitude = X*X + Y*Y;
//                // Don't trust the angle if the magnitude is small compared to the y value
//                if (magnitude * 4 >= Z*Z) {
//                    float OneEightyOverPi = 57.29577957855f;
//                    float angle = (float)Math.atan2(-Y, X) * OneEightyOverPi;
//                    orientation = 90 - (int)Math.round(angle);
//                    // normalize to 0 - 359 range
//                    while (orientation >= 360) {
//                        orientation -= 360;
//                    }
//                    while (orientation < 0) {
//                        orientation += 360;
//                    }
//                }
//
//                if (orientation>45&&orientation<135) {
////                  getActivity().setRequestedOrientation(8);
//                    //根据系统来决定屏幕旋转的方向
//                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//                }else if (orientation>135&&orientation<225){
//                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
////                  getActivity().setRequestedOrientation(9);
//                }else if (orientation>225&&orientation<315){
//                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
////                  getActivity().setRequestedOrientation(0);
//                }else if ((orientation>315&&orientation<360)||(orientation>0&&orientation<45)){
////                  getActivity().setRequestedOrientation(1);
//                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//                }
//
//
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//                //nothing
//            }
//        };
//        getDkActivity().addSensorListener(sensor, mSensorListener, SensorManager.SENSOR_DELAY_UI);
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

            if (!isScreenOriatationPortrait(LandVoiceActivity.this)) {// 当屏幕是横屏时
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
            DialogView.dismiss( LandVoiceActivity.this);

        }
    };

    MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
//            if (isPlayFlag == false) return true;
           /* new AlertDialog.Builder(LandVoiceActivity.this)
                    .setMessage("抱歉,播放错误")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    stopVideoPlaying();
                                }
                            })
                    .setCancelable(false)
                    .show();*/

            DialogView.dismiss(LandVoiceActivity.this);
            try {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.video_error_alertdialog, null, true);

                final VideoErrorDialog videoErrorDialog = new VideoErrorDialog(LandVoiceActivity.this, view);

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
//        video_rela.setVisibility(View.GONE);
//        videolist.setVisibility(View.VISIBLE);
//        setVisable(View.VISIBLE);
        videoView.pause();
        videoView.stopPlayback();
        if (!isScreenOriatationPortrait(LandVoiceActivity.this)) {// 当屏幕是横屏时
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
                if (isPortrait) {// 当屏幕是竖屏时
                    // 点击后变横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 设置当前activity为横屏
                    isPortrait = false;
                    videoname.setVisibility(View.GONE);
//                    change_screen.setBackground(getResources().getDrawable(R.drawable.icon_no_full));
                } else {
//                    change_screen.setBackground(getResources().getDrawable(R.drawable.icon_full_screen));
                    isPortrait = true;
                    videoname.setVisibility(View.VISIBLE);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置当前activity为竖屏
                    //显示其他组件
                }
                break;
        }
    }
}
