package com.marco.finbill.sql.currency_code;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currency_code_table")
public class CurrencyCode {
    @PrimaryKey
    @NonNull
    private String currencyString;

    public CurrencyCode(@NonNull String currencyString) {
        this.currencyString = currencyString;
    }

    @NonNull
    public String getCurrencyString() {
        return currencyString;
    }

    public void getCurrencyString(String currencyCode) {
        this.currencyString = currencyString;
    }
}
