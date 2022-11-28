package com.marco.finbill.sql.transaction.expense;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class ExpenseFromAccount {
    private int fromExpense;
    @Embedded
    private Account account;

    public ExpenseFromAccount(int fromExpense, Account account) {
        this.fromExpense = fromExpense;
        this.account = account;
    }

    public int getFromExpense() {
        return fromExpense;
    }

    public void setFromExpense(int fromExpense) {
        this.fromExpense = fromExpense;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
