package com.marco.finbill.sql.transaction.expense;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey private int expenseId;
    private int fromExpense;
    private int toExpense;

    public Expense(int expenseId, int fromExpense, int toExpense) {
        this.expenseId = expenseId;
        this.fromExpense = fromExpense;
        this.toExpense = toExpense;
    }

    public Expense() {
        this.expenseId = 0;
        this.fromExpense = 0;
        this.toExpense = 0;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getFromExpense() {
        return fromExpense;
    }

    public void setFromExpense(int fromExpense) {
        this.fromExpense = fromExpense;
    }

    public int getToExpense() {
        return toExpense;
    }

    public void setToExpense(int toExpense) {
        this.toExpense = toExpense;
    }
}
