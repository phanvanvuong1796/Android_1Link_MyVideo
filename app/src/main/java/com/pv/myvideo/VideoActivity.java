package com.pv.myvideo;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    private String mVideoPath;
    private VideoView mVideoView;
    private Context mContext;
    private ArrayList<String> mVideoList;
    private int mVideoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mContext = this;
        mVideoPath = getIntent().getExtras().getString(MainActivity.VIDEO_PATH);
        mVideoList = getIntent().getExtras().getStringArrayList(MainActivity.VIDEO_LIST);
        mVideoPosition = getIntent().getExtras().getInt(MainActivity.VIDEO_POSTIION);
        mVideoView = (VideoView) findViewById(R.id.video_view);

        mVideoView.setVideoPath(mVideoPath);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.start();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onNextVideo();
            }
        });

        mediaController.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextVideo();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPrevVideo();
            }
        });
    }

    public void onNextVideo(){
        if(mVideoPosition == mVideoList.size()-1){
            Toast.makeText(mContext, "End!", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else{
            Toast.makeText(mContext, "Đây là next!", Toast.LENGTH_SHORT).show();
            mVideoView.stopPlayback();
            mVideoView.setVideoPath(mVideoList.get(++mVideoPosition));
            mVideoView.start();
        }
    }

    public void onPrevVideo(){
        if(mVideoPosition == 0){
            Toast.makeText(mContext, "Start!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, "Đây là prev!", Toast.LENGTH_SHORT).show();
            mVideoView.stopPlayback();
            mVideoView.setVideoPath(mVideoList.get(--mVideoPosition));
            mVideoView.start();
        }
    }
}
