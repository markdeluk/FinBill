package com.marco.finbill.sql.transaction.expense;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ExpenseToCategoryDao {
    @Query("SELECT E.toExpense, C.*" +
            "FROM category_table C, expense_table E " +
            "WHERE C.categoryId = E.toExpense " +
            "AND E.toExpense = :expenseId")
    public ExpenseToCategory getExpenseToCategoryById(int expenseId);
}
