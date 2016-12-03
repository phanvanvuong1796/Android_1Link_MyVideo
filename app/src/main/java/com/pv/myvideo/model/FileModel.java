package com.pv.myvideo.model;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.widget.ImageView;

/**
 * Created by phanvuong on 11/28/16.
 */

public class FileModel {
    private String filePath;
    private String fileName;

    public FileModel(String imgLink, String fileName) {
        this.filePath = imgLink;
        this.fileName = fileName;
    }

    public String getImgLink() {
        return filePath;
    }

    public void setImgLink(String imgLink) {
        this.filePath = imgLink;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imgView, String url){
        Bitmap videoThumbnail = ThumbnailUtils.createVideoThumbnail(url, MediaStore.Video.Thumbnails.MICRO_KIND);
        imgView.setImageBitmap(videoThumbnail);
    }
}
