package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class IncomeToAccount {
    public int incomeId;
    @Embedded
    public Account toIncome;
}
