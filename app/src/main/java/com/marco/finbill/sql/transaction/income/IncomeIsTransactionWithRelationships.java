package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.Transaction;

public class IncomeIsTransactionWithRelationships {
    public int incomeId;
    @Embedded
    public Transaction transaction;
    @Embedded
    public Category fromIncome;
    @Embedded
    public Account toIncome;
}
