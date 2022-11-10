package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.transaction.Transaction;
import com.marco.finbill.sql.transaction.expense.ExpenseWithRelationships;

public class TransactionIsIncomeWithRelationships {

    @Embedded
    public Transaction transaction;

    @Relation(
            parentColumn = "transactionId",
            entityColumn = "incomeId"
    )
    public IncomeWithRelationships incomeWithRelationships;
}
