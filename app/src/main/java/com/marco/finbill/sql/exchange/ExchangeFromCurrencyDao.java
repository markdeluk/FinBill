package com.marco.finbill.sql.exchange;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExchangeFromCurrencyDao {

    @Query("SELECT EX.exchangeFromCurrencyId, CU.* " +
            "FROM exchange_table EX, currency_table CU " +
            "WHERE EX.exchangeFromCurrencyId = CU.currencyId")
    public LiveData<List<ExchangeFromCurrency>> getAllExchangesFromCurrency();
}
