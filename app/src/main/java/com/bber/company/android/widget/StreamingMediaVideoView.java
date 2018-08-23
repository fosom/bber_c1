package com.bber.company.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;


/**
 * Created by Administrator on 2015/10/9 0009.
 */
public class StreamingMediaVideoView extends VideoView {


    private int videoWidth;
    private int videoHeight;

    public StreamingMediaVideoView(Context context) {
        super(context);
    }

    public StreamingMediaVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StreamingMediaVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(videoWidth, widthMeasureSpec);
        int height = getDefaultSize(videoHeight, heightMeasureSpec);
        if (videoWidth > 0 && videoHeight > 0) {
            if (videoWidth * height > width * videoHeight) {
                height = width * videoHeight / videoWidth;
            } else if (videoWidth * height < width * videoHeight) {
                width = height * videoWidth / videoHeight;
            }
        }
        setMeasuredDimension(width, height);
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }


}
