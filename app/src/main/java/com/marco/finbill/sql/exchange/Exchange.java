package com.marco.finbill.sql.exchange;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.util.Date;

@Entity(tableName = "Exchange")
public class Exchange {
    @PrimaryKey(autoGenerate = true)
    private int exchangeId;
    private Currency fromCurrency;
    private Currency toCurrency;
    private float rate;
    private Date date;
}
