package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.transaction.Transaction;

public class ExpenseIsTransaction {
    public int expenseId;
    @Embedded
    public Transaction transaction;
}
