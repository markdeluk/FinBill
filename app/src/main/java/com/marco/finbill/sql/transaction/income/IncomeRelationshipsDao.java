package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface IncomeRelationshipsDao {
    @Transaction
    @Query("SELECT * FROM income_table")
    LiveData<List<IncomeRelationships>> getAllIncomeIsTransactionWithRelationships();
}