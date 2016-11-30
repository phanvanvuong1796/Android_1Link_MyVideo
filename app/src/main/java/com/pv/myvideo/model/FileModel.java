package com.pv.myvideo.model;

/**
 * Created by phanvuong on 11/28/16.
 */

public class FileModel {
    private String imgLink;
    private String fileName;

    public FileModel(String imgLink, String fileName) {
        this.imgLink = imgLink;
        this.fileName = fileName;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
