package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.Transaction;

public class ExpenseIsTransactionWithRelationships {
    private int expenseId;
    @Embedded
    private Transaction transaction;
    @Embedded
    private Account fromExpense;
    @Embedded
    private Category toExpense;

    public ExpenseIsTransactionWithRelationships(int expenseId, Transaction transaction, Account fromExpense, Category toExpense) {
        this.expenseId = expenseId;
        this.transaction = transaction;
        this.fromExpense = fromExpense;
        this.toExpense = toExpense;
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
}
