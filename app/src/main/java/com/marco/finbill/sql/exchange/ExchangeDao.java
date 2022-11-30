package com.marco.finbill.sql.exchange;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExchangeDao {

    @Insert
    void insertExchange(Exchange exchange);

    @Update
    void updateExchange(Exchange exchange);

    @Delete
    void deleteExchange(Exchange exchange);

    @Query("SELECT * FROM exchange_table WHERE exchangeFromCurrencyId = :exchangeFromCurrencyId AND exchangeToCurrencyId = :exchangeToCurrencyId")
    Exchange getExchangeByCurrencies(int exchangeFromCurrencyId, int exchangeToCurrencyId);

    @Query("SELECT * FROM exchange_table")
    LiveData<List<Exchange>> getAllExchanges();

    @Query("SELECT exchangeId FROM exchange_table ORDER BY exchangeId DESC LIMIT 1")
    LiveData<Integer> getLastExchangeId();

    @Query("DELETE FROM exchange_table")
    void deleteAllExchanges();

}
