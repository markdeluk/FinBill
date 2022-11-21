package com.marco.finbill.sql.exchange;

import android.icu.util.Currency;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "exchange_table", primaryKeys = {"exchangeFromCurrency", "exchangeToCurrency"})
public class Exchange {
    @NonNull
    private Currency exchangeFromCurrency;
    @NonNull
    private Currency exchangeToCurrency;
    private double exchangeRate;

    public Exchange(@NonNull Currency exchangeFromCurrency, @NonNull Currency exchangeToCurrency, double exchangeRate) {
        this.exchangeFromCurrency = exchangeFromCurrency;
        this.exchangeToCurrency = exchangeToCurrency;
        this.exchangeRate = exchangeRate;
    }

    @NonNull
    public Currency getExchangeFromCurrency() {
        return exchangeFromCurrency;
    }

    public void setExchangeFromCurrency(@NonNull Currency exchangeFromCurrency) {
        this.exchangeFromCurrency = exchangeFromCurrency;
    }

    @NonNull
    public Currency getExchangeToCurrency() {
        return exchangeToCurrency;
    }

    public void setExchangeToCurrency(@NonNull Currency exchangeToCurrency) {
        this.exchangeToCurrency = exchangeToCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
