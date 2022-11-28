package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class IncomeToAccount {
    private int incomeId;
    @Embedded
    private Account toIncome;

    public IncomeToAccount(int incomeId, Account toIncome) {
        this.incomeId = incomeId;
        this.toIncome = toIncome;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public Account getToIncome() {
        return toIncome;
    }

    public void setToIncome(Account toIncome) {
        this.toIncome = toIncome;
    }
}
