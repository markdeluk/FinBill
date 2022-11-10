package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.transaction.Transaction;

public class TransactionIsIncome {

    @Embedded
    Transaction transaction;

    @Relation(
            parentColumn = "transactionId",
            entityColumn = "incomeId"
    )
    Income income;
}