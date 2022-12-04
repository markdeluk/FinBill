package com.marco.finbill.sql.exchange;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface ExchangeWithCurrenciesDao {
    @Transaction
    @Query("SELECT * FROM exchange_table")
    LiveData<List<ExchangeWithCurrencies>> getAllExchangesWithCurrencies();
}