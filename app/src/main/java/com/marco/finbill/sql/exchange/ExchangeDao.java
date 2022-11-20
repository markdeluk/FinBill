package com.marco.finbill.sql.exchange;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.marco.finbill.sql.category.Category;

import java.util.List;

@Dao
public interface ExchangeDao {

    @Insert
    void insertExchange(Exchange exchange);

    @Update
    void updateExchange(Exchange exchange);

    @Delete
    void deleteExchange(Exchange exchange);

    @Query("SELECT * FROM exchange_table WHERE exchangeId = :id")
    Exchange getExchangeById(int id);

    @Query("SELECT * FROM exchange_table")
    LiveData<List<Exchange>> getAllExchanges();

    @Query("DELETE FROM exchange_table")
    void deleteAllExchanges();

}
