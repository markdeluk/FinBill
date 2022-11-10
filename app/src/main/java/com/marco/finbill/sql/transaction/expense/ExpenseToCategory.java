package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.category.Category;

public class ExpenseToCategory {
    @Embedded
    Expense expense;

    @Relation(
            parentColumn = "categoryId",
            entityColumn = "toExpense"
    )
    Category category;
}
