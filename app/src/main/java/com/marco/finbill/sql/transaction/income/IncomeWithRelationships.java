package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;

public class IncomeWithRelationships {

    @Embedded
    public Income income;

    @Relation(
            parentColumn = "fromIncome",
            entityColumn = "categoryId"
    )
    public Category fromIncome;

    @Relation(
            parentColumn = "toIncome",
            entityColumn = "accountId"
    )
    public Account toIncome;
}
