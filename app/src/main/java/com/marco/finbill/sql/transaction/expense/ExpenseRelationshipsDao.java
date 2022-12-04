package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface ExpenseRelationshipsDao {
    @Transaction
    @Query("SELECT * FROM expense_table")
    LiveData<List<ExpenseRelationships>> getAllExpenseIsTransactionWithRelationships();
}