package com.marco.finbill.sql.currency_code;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyCodeDao {
    @Insert
    void insertCurrencyCode(CurrencyCode currencyCode);

    @Update
    void updateCurrencyCode(CurrencyCode currencyCode);

    @Delete
    void deleteCurrencyCode(CurrencyCode currencyCode);

    @Query("SELECT * FROM currency_code_table")
    LiveData<List<CurrencyCode>> getAllCurrencyCodes();

    @Query("DELETE FROM currency_code_table")
    void deleteAllCurrencyCodes();
}
