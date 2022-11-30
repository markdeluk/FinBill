package com.marco.finbill.sql.currency;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyDao {
    @Insert
    void insertCurrency(Currency currency);

    @Update
    void updateCurrency(Currency currency);

    @Delete
    void deleteCurrency(Currency currency);

    @Query("SELECT * FROM currency_table WHERE currencyString = :currencyString")
    LiveData<Currency> getCurrencyByString(String currencyString);

    @Query("SELECT * FROM currency_table ORDER BY currencyString ASC")
    LiveData<List<Currency>> getAllCurrencies();

    @Query("SELECT currencyId FROM currency_table ORDER BY currencyString DESC LIMIT 1")
    LiveData<Integer> getLastCurrencyId();

    @Query("DELETE FROM currency_table")
    void deleteAllCurrencies();
}
