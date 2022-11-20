package com.marco.finbill.sql.exchange.exchange_latest_update;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExchangeLatestUpdateDao {
    @Insert
    void insertExchangeLatestUpdate(ExchangeLatestUpdate exchangeLatestUpdate);

    @Update
    void updateExchangeLatestUpdate(ExchangeLatestUpdate exchangeLatestUpdate);

    @Delete
    void deleteExchangeLatestUpdate(ExchangeLatestUpdate exchangeLatestUpdate);

    @Query("SELECT * FROM exchange_latest_update_table")
    ExchangeLatestUpdate getExchangeLatestUpdate();
}
