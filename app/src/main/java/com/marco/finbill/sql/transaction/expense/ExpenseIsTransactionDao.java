package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseIsTransactionDao {
    @Query("SELECT E.expenseId, T.*" +
            "FROM transaction_table T, expense_table E " +
            "WHERE T.transactionId = E.expenseId " +
            "AND E.expenseId = :expenseId")
    public ExpenseIsTransaction getExpenseIsTransactionById(int expenseId);
}
