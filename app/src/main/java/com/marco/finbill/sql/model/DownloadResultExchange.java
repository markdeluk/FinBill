package com.marco.finbill.sql.model;

import com.marco.finbill.enums.DownloadStatus;

public class DownloadResultExchange {

    private DownloadStatus downloadStatus;
    private String fromCurrencyString;
    private String toCurrencyString;
    private Double exchangeRate;

    public DownloadResultExchange(DownloadStatus downloadStatus, String fromCurrencyString, String toCurrencyString, Double exchangeRate) {
        this.downloadStatus = downloadStatus;
        this.fromCurrencyString = fromCurrencyString;
        this.toCurrencyString = toCurrencyString;
        this.exchangeRate = exchangeRate;
    }

    public DownloadResultExchange() {
        this.downloadStatus = null;
        this.fromCurrencyString = null;
        this.toCurrencyString = null;
        this.exchangeRate = null;
    }

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getFromCurrencyString() {
        return fromCurrencyString;
    }

    public void setFromCurrencyString(String fromCurrencyString) {
        this.fromCurrencyString = fromCurrencyString;
    }

    public String getToCurrencyString() {
        return toCurrencyString;
    }

    public void setToCurrencyString(String toCurrencyString) {
        this.toCurrencyString = toCurrencyString;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
