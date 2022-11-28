package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.category.Category;

public class ExpenseToCategory {
    private int toExpense;
    @Embedded
    private Category category;

    public ExpenseToCategory(int toExpense, Category category) {
        this.toExpense = toExpense;
        this.category = category;
    }

    public int getToExpense() {
        return toExpense;
    }

    public void setToExpense(int toExpense) {
        this.toExpense = toExpense;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
