package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;

public class ExpenseRelationships {

    @Embedded
    private Expense expense;

    @Relation(
            parentColumn = "expenseId",
            entityColumn = "transactionId"
    )
    private TransactionHasCurrency transactionHasCurrency;

    @Relation(
            parentColumn = "fromExpense",
            entityColumn = "accountId"
    )
    private Account fromExpense;

    @Relation(
            parentColumn = "toExpense",
            entityColumn = "categoryId"
    )
    private Category toExpense;

    public ExpenseRelationships(Expense expense, TransactionHasCurrency transactionHasCurrency, Account fromExpense, Category toExpense) {
        this.expense = expense;
        this.transactionHasCurrency = transactionHasCurrency;
        this.fromExpense = fromExpense;
        this.toExpense = toExpense;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public TransactionHasCurrency getTransactionHasCurrency() {
        return transactionHasCurrency;
    }

    public void setTransactionHasCurrency(TransactionHasCurrency transactionHasCurrency) {
        this.transactionHasCurrency = transactionHasCurrency;
    }

    public Account getFromExpense() {
        return fromExpense;
    }

    public void setFromExpense(Account fromExpense) {
        this.fromExpense = fromExpense;
    }

    public Category getToExpense() {
        return toExpense;
    }

    public void setToExpense(Category toExpense) {
        this.toExpense = toExpense;
    }

    public boolean equals(ExpenseRelationships expenseRelationships) {
        return expenseRelationships.getExpense().equals(this.expense) &&
                expenseRelationships.getTransactionHasCurrency().equals(this.transactionHasCurrency) &&
                expenseRelationships.getFromExpense().equals(this.fromExpense) &&
                expenseRelationships.getToExpense().equals(this.toExpense);
    }
}
