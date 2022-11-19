package com.marco.finbill.sql.exchange;

import android.icu.util.Currency;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "exchange_table")
public class Exchange {
    @PrimaryKey(autoGenerate = true)
    private int exchangeId;
    private Currency exchangeFromCurrency;
    private Currency exchangeToCurrency;
    private double exchangeRate;
    private Date exchangeLastUpdate;

    public Exchange(Currency exchangeFromCurrency, Currency exchangeToCurrency, double exchangeRate, Date exchangeLastUpdate) {
        this.exchangeFromCurrency = exchangeFromCurrency;
        this.exchangeToCurrency = exchangeToCurrency;
        this.exchangeRate = exchangeRate;
        this.exchangeLastUpdate = exchangeLastUpdate;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Currency getExchangeFromCurrency() {
        return exchangeFromCurrency;
    }

    public void setExchangeFromCurrency(Currency exchangeFromCurrency) {
        this.exchangeFromCurrency = exchangeFromCurrency;
    }

    public Currency getExchangeToCurrency() {
        return exchangeToCurrency;
    }

    public void setExchangeToCurrency(Currency exchangeToCurrency) {
        this.exchangeToCurrency = exchangeToCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getExchangeLastUpdate() {
        return exchangeLastUpdate;
    }

    public void setExchangeLastUpdate(Date exchangeLastUpdate) {
        this.exchangeLastUpdate = exchangeLastUpdate;
    }
}
