package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class ExpenseFromAccount {
    public int fromExpense;
    @Embedded
    public Account account;
}
