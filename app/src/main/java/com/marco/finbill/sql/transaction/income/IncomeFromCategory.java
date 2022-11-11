package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.category.Category;

public class IncomeFromCategory {
    public int incomeId;
    @Embedded
    public Category category;
}
