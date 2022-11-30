package com.marco.finbill.sql.currency;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "currency_table", indices = {@Index(value = {"currencyString"}, unique = true)})
public class Currency {
    @PrimaryKey(autoGenerate = true)
    private int currencyId;
    @NonNull
    private String currencyString;

    public Currency(@NonNull String currencyString) {
        this.currencyString = currencyString;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @NonNull
    public String getCurrencyString() {
        return currencyString;
    }

    public void getCurrencyString(String currencyCode) {
        this.currencyString = currencyString;
    }
}
