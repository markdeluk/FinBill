package com.marco.finbill.sql.exchange;

import android.icu.util.Currency;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "exchange_table", primaryKeys = {"exchangeFromCurrencyId", "exchangeToCurrencyId"})
public class Exchange {
    private int exchangeFromCurrencyId;
    private int exchangeToCurrencyId;
    private double exchangeRate;

    public Exchange(int exchangeFromCurrencyId, int exchangeToCurrencyId, double exchangeRate) {
        this.exchangeFromCurrencyId = exchangeFromCurrencyId;
        this.exchangeToCurrencyId = exchangeToCurrencyId;
        this.exchangeRate = exchangeRate;
    }

    public int getExchangeFromCurrencyId() {
        return exchangeFromCurrencyId;
    }

    public void setExchangeFromCurrencyId(int exchangeFromCurrencyId) {
        this.exchangeFromCurrencyId = exchangeFromCurrencyId;
    }

    public int getExchangeToCurrencyId() {
        return exchangeToCurrencyId;
    }

    public void setExchangeToCurrencyId(int exchangeToCurrencyId) {
        this.exchangeToCurrencyId = exchangeToCurrencyId;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
