package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;

public class ExpenseWithRelationships {

    @Embedded
    public Expense expense;

    @Relation(
            parentColumn = "fromExpense",
            entityColumn = "accountId"
    )
    public Account fromExpense;

    @Relation(
            parentColumn = "toExpense",
            entityColumn = "categoryId"
    )
    public Category toExpense;

}
