package com.marco.finbill.sql.exchange;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExchangeToCurrencyDao {

    @Query("SELECT EX.exchangeToCurrencyId, CU.* " +
            "FROM exchange_table EX, currency_table CU " +
            "WHERE EX.exchangeToCurrencyId = CU.currencyId")
    public LiveData<List<ExchangeToCurrency>> getAllExchangesToCurrency();
}
