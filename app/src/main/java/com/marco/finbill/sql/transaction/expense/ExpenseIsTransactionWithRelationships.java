package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;

public class ExpenseIsTransactionWithRelationships {

    private int expenseId;
    @Embedded
    private TransactionHasCurrency transactionHasCurrency;
    @Embedded
    private Account fromExpense;
    @Embedded
    private Category toExpense;

    public ExpenseIsTransactionWithRelationships(int expenseId, TransactionHasCurrency transactionHasCurrency, Account fromExpense, Category toExpense) {
        this.expenseId = expenseId;
        this.transactionHasCurrency = transactionHasCurrency;
        this.fromExpense = fromExpense;
        this.toExpense = toExpense;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
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
        return expenseIsTransactionWithRelationships.getExpenseId() == this.expenseId &&
                expenseIsTransactionWithRelationships.getTransactionHasCurrency().equals(this.transactionHasCurrency) &&
                expenseIsTransactionWithRelationships.getFromExpense().equals(this.fromExpense) &&
                expenseIsTransactionWithRelationships.getToExpense().equals(this.toExpense);
    }
}
