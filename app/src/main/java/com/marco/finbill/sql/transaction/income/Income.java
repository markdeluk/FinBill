package com.marco.finbill.sql.transaction.income;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;

@Entity(tableName = "income_table")
public class Income {

    @PrimaryKey public int incomeId;
    public int fromIncome;
    public int toIncome;

}
