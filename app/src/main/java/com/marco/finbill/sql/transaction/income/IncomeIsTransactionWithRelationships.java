package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.Transaction;

public class IncomeIsTransactionWithRelationships {
    private int incomeId;
    @Embedded
    private Transaction transaction;
    @Embedded
    private Category fromIncome;
    @Embedded
    private Account toIncome;

    public IncomeIsTransactionWithRelationships(int incomeId, Transaction transaction, Category fromIncome, Account toIncome) {
        this.incomeId = incomeId;
        this.transaction = transaction;
        this.fromIncome = fromIncome;
        this.toIncome = toIncome;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Category getFromIncome() {
        return fromIncome;
    }

    public void setFromIncome(Category fromIncome) {
        this.fromIncome = fromIncome;
    }

    public Account getToIncome() {
        return toIncome;
    }

    public void setToIncome(Account toIncome) {
        this.toIncome = toIncome;
    }

    public boolean equals(IncomeIsTransactionWithRelationships incomeIsTransactionWithRelationships) {
        return this.incomeId == incomeIsTransactionWithRelationships.incomeId && this.transaction.equals(incomeIsTransactionWithRelationships.transaction) && this.fromIncome.equals(incomeIsTransactionWithRelationships.fromIncome) && this.toIncome.equals(incomeIsTransactionWithRelationships.toIncome);
    }
}
