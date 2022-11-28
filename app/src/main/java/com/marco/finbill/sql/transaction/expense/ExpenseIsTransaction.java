package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.transaction.Transaction;

public class ExpenseIsTransaction {
    private int expenseId;
    @Embedded
    private Transaction transaction;

    public ExpenseIsTransaction(int expenseId, Transaction transaction) {
        this.expenseId = expenseId;
        this.transaction = transaction;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
