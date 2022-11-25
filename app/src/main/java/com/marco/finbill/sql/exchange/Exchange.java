package com.marco.finbill.sql.exchange;

import android.icu.util.Currency;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "exchange_table", primaryKeys = {"exchangeFromCurrency", "exchangeToCurrency"})
public class Exchange {
    @NonNull
    private String exchangeFromCurrency;
    @NonNull
    private String exchangeToCurrency;
    private double exchangeRate;

    public Exchange(@NonNull String exchangeFromCurrency, @NonNull String exchangeToCurrency, double exchangeRate) {
        this.exchangeFromCurrency = exchangeFromCurrency;
        this.exchangeToCurrency = exchangeToCurrency;
        this.exchangeRate = exchangeRate;
    }

    @NonNull
    public String getExchangeFromCurrency() {
        return exchangeFromCurrency;
    }

    public void setExchangeFromCurrency(@NonNull String exchangeFromCurrency) {
        this.exchangeFromCurrency = exchangeFromCurrency;
    }

    @NonNull
    public String getExchangeToCurrency() {
        return exchangeToCurrency;
    }

    public void setExchangeToCurrency(@NonNull String exchangeToCurrency) {
        this.exchangeToCurrency = exchangeToCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
