package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insertExpense(Expense expense);

    @Update
    void updateExpense(Expense expense);

    @Delete
    void deleteExpense(Expense expense);

    @Transaction
    @Query("SELECT * FROM expense_table WHERE expenseId = :expenseId")
    TransactionIsExpenseWithRelationships getExpenseById(int expenseId);

    @Transaction
    @Query("SELECT * FROM expense_table")
    LiveData<List<TransactionIsExpenseWithRelationships>> getAllExpenses();

    @Query("DELETE FROM expense_table")
    void deleteAllExpenses();

}
