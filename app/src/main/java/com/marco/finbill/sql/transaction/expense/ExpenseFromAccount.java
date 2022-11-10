package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

public class ExpenseFromAccount {
    @Embedded
    Expense expense;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "fromExpense"
    )
    Account account;
}
