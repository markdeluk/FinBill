package com.marco.finbill.sql.model;

import com.marco.finbill.enums.DownloadStatus;

public class DownloadResultNumber {

    private DownloadStatus downloadStatus;
    private Integer downloadedItems;

    public DownloadResultNumber(DownloadStatus downloadStatus, Integer downloadedItems) {
        this.downloadStatus = downloadStatus;
        this.downloadedItems = downloadedItems;
    }

    public DownloadResultNumber() {
        this.downloadedItems = null;
        this.downloadStatus = null;
    }

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public Integer getDownloadedItems() {
        return downloadedItems;
    }

    public void setDownloadedItems(Integer downloadedItems) {
        this.downloadedItems = downloadedItems;
    }
}
