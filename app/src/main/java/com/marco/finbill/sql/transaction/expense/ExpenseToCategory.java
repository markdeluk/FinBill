package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.category.Category;

public class ExpenseToCategory {
    public int toExpense;
    @Embedded
    public Category category;
}
