package com.marco.finbill.sql.exchange.exchange_latest_update;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "exchange_latest_update_table")
public class ExchangeLatestUpdate {
    @PrimaryKey(autoGenerate = true)
    private int exchangeLatestUpdateId;
    private Date exchangeLatestUpdate;

    public ExchangeLatestUpdate(Date exchangeLatestUpdate) {
        this.exchangeLatestUpdate = exchangeLatestUpdate;
    }

    public Date getExchangeLatestUpdate() {
        return exchangeLatestUpdate;
    }

    public void setExchangeLatestUpdate(Date exchangeLatestUpdate) {
        this.exchangeLatestUpdate = exchangeLatestUpdate;
    }
}
