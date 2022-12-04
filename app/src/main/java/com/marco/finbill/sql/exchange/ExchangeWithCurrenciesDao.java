package com.marco.finbill.sql.exchange;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExchangeWithCurrenciesDao {

    @Query("SELECT FC.*, TC.* " +
            "FROM exchange_table EX, currency_table FC, currency_table TC " +
            "WHERE EX.exchangeFromCurrencyId = FC.currencyId " +
            "AND EX.exchangeToCurrencyId = TC.currencyId")
    public LiveData<List<ExchangeWithCurrencies>> getAllExchangesWithCurrencies();
}
