package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;
public class IncomeRelationships {

    @Embedded
    private Income income;

    @Relation(
            entity = Transaction.class,
            parentColumn = "incomeId",
            entityColumn = "transactionId"
    )
    private TransactionHasCurrency transactionHasCurrency;

    @Relation(
            parentColumn = "fromIncome",
            entityColumn = "categoryId"
    )
    private Category fromIncome;

    @Relation(
            parentColumn = "toIncome",
            entityColumn = "accountId"
    )
    private Account toIncome;

    public IncomeRelationships(Income income, TransactionHasCurrency transactionHasCurrency, Category fromIncome, Account toIncome) {
        this.income = income;
        this.transactionHasCurrency = transactionHasCurrency;
        this.fromIncome = fromIncome;
        this.toIncome = toIncome;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
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

    public boolean equals(IncomeRelationships incomeRelationships) {
        return this.income.equals(incomeRelationships.income) &&
                this.transactionHasCurrency.equals(incomeRelationships.transactionHasCurrency) &&
                this.fromIncome.equals(incomeRelationships.fromIncome) &&
                this.toIncome.equals(incomeRelationships.toIncome);
    }
}