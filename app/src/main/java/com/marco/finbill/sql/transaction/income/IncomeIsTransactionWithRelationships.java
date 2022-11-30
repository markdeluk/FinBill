package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;

public class IncomeIsTransactionWithRelationships {
    private int incomeId;
    @Embedded
    private TransactionHasCurrency transactionHasCurrency;
    @Embedded
    private Category fromIncome;
    @Embedded
    private Account toIncome;

    public IncomeIsTransactionWithRelationships(int incomeId, TransactionHasCurrency transactionHasCurrency, Category fromIncome, Account toIncome) {
        this.incomeId = incomeId;
        this.transactionHasCurrency = transactionHasCurrency;
        this.fromIncome = fromIncome;
        this.toIncome = toIncome;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public TransactionHasCurrency getTransactionHasCurrency() {
        return transactionHasCurrency;
    }

    public void setTransactionHasCurrency(TransactionHasCurrency transactionHasCurrency) {
        this.transactionHasCurrency = transactionHasCurrency;
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
        return this.incomeId == incomeIsTransactionWithRelationships.incomeId && this.transactionHasCurrency.equals(incomeIsTransactionWithRelationships.transactionHasCurrency) && this.fromIncome.equals(incomeIsTransactionWithRelationships.fromIncome) && this.toIncome.equals(incomeIsTransactionWithRelationships.toIncome);
    }
}
