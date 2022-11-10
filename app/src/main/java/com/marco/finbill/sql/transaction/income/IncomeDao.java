package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.marco.finbill.sql.transaction.Transaction;

import java.util.List;

@Dao
public interface IncomeDao {

    @Insert
    void insertIncome(Income expense);

    @Update
    void updateIncome(Income expense);

    @Delete
    void deleteIncome(Income expense);

    @androidx.room.Transaction
    @Query("SELECT * FROM income_table WHERE incomeId = :incomeId")
    TransactionIsIncomeWithRelationships getIncomeById(int incomeId);

    @androidx.room.Transaction
    @Query("SELECT * FROM income_table")
    LiveData<List<TransactionIsIncomeWithRelationships>> getAllIncomes();

    @Query("DELETE FROM income_table")
    void deleteAllIncomes();
    
}
