package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

import java.util.List;

public class ExpenseFromAccount {
    @Embedded
    Account account;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "fromExpense"
    )
    LiveData<List<Expense>> expenses;
}
