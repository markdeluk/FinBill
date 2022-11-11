package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseFromAccountDao {
    @Query("SELECT E.fromExpense, A.* " +
            "FROM expense_table E, account_table A " +
            "WHERE E.fromExpense = A.accountId " +
            "AND E.expenseId = :expenseId")
    public ExpenseFromAccount getExpenseFromAccountById(int expenseId);
}
