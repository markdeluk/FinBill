package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;

public class ExpenseIsTransactionWithRelationships {

    @Embedded
    private Expense expense;

    @Relation(
            entity = Transaction.class,
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

    public ExpenseIsTransactionWithRelationships(Expense expense, TransactionHasCurrency transactionHasCurrency, Account fromExpense, Category toExpense) {
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

    public boolean equals(ExpenseIsTransactionWithRelationships expenseIsTransactionWithRelationships) {
        return expenseIsTransactionWithRelationships.getExpense().equals(this.expense) &&
                expenseIsTransactionWithRelationships.getTransactionHasCurrency().equals(this.transactionHasCurrency) &&
                expenseIsTransactionWithRelationships.getFromExpense().equals(this.fromExpense) &&
                expenseIsTransactionWithRelationships.getToExpense().equals(this.toExpense);
    }
}
