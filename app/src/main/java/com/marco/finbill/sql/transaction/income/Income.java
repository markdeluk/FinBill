package com.marco.finbill.sql.transaction.income;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;

@Entity(tableName = "income_table")
public class Income {

    @PrimaryKey private int incomeId;
    private int fromIncome;
    private int toIncome;

    public Income(int incomeId, int fromIncome, int toIncome) {
        this.incomeId = incomeId;
        this.fromIncome = fromIncome;
        this.toIncome = toIncome;
    }

    @Ignore
    public Income() {
        this.incomeId = 0;
        this.fromIncome = 0;
        this.toIncome = 0;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public int getFromIncome() {
        return fromIncome;
    }

    public void setFromIncome(int fromIncome) {
        this.fromIncome = fromIncome;
    }

    public int getToIncome() {
        return toIncome;
    }

    public void setToIncome(int toIncome) {
        this.toIncome = toIncome;
    }

}
