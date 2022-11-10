package com.marco.finbill.sql.transaction.expense;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey int expenseId;
    int fromExpense;
    int toExpense;

}
