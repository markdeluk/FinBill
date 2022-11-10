package com.marco.finbill.sql.transaction.expense;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey public int expenseId;
    public int fromExpense;
    public int toExpense;

}
