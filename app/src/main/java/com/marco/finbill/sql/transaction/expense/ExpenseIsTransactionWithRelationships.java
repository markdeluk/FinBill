package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.Transaction;

public class ExpenseIsTransactionWithRelationships {
    public int expenseId;
    @Embedded
    public Transaction transaction;
    @Embedded
    public Account fromExpense;
    @Embedded
    public Category toExpense;
}
