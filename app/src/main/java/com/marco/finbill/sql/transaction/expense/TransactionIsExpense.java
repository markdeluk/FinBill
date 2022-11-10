package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.transaction.Transaction;

public class TransactionIsExpense {
    @Embedded
    Transaction transaction;

    @Relation(
            parentColumn = "transactionId",
            entityColumn = "expenseId"
    )
    Expense expense;
}
