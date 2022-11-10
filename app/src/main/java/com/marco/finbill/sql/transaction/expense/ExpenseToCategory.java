package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.category.Category;

import java.util.List;

public class ExpenseToCategory {

    @Embedded
    Category category;

    @Relation(
            parentColumn = "categoryId",
            entityColumn = "toExpense"
    )
    LiveData<List<Expense>> expenses;
}
