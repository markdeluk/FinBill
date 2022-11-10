package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;

public class ExpenseWithRelationships {

    @Embedded
    public Expense expense;

    @Relation(
            entity = Account.class,
            parentColumn = "fromExpense",
            entityColumn = "accountId"
    )
    public Account fromExpense;

    @Relation(
            entity = Category.class,
            parentColumn = "toExpense",
            entityColumn = "categoryId"
    )
    public Category toExpense;

}
