package com.pv.myvideo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.pv.myvideo.adapter.FileAdapter;
import com.pv.myvideo.model.FileModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String VIDEO_PATH = "video path";
    public static final String VIDEO_LIST = "video list";
    public static final String VIDEO_POSTIION = "video position";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<String> mVideoPath;
//Hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoPath = new ArrayList<>();
        final List<FileModel> listVideo = getListVideo(this);
        mContext = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.list_video);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FileAdapter fileAdapter = new FileAdapter(listVideo);
        fileAdapter.setCallBack(new FileAdapter.CallBack() {
            @Override
            public void onClick(FileModel fileModel) {
                Toast.makeText(mContext, listVideo.indexOf(fileModel)+" "+fileModel.getImgLink(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra(VIDEO_PATH, fileModel.getImgLink());
                intent.putStringArrayListExtra(VIDEO_LIST, mVideoPath);
                intent.putExtra(VIDEO_POSTIION, listVideo.indexOf(fileModel));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(fileAdapter);


    }

    public List<FileModel> getListVideo(Context context) {
        List<FileModel> fileModels = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA };
        String videoName;
        String videoPath;
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                videoPath = c.getString(0);
                mVideoPath.add(videoPath);
                String[] split = videoPath.split("/");
                videoName = split[split.length-1];
                fileModels.add(new FileModel(videoPath, videoName));
            }
            c.close();
        }
        return fileModels;
    }

}
